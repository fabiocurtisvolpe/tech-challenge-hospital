package com.adjt.pagamento.amqp.service;

import com.adjt.pagamento.core.usecase.AtualizarPagamentoErroUseCase;
import com.adjt.pagamento.core.usecase.AtualizarPagamentoSucessoUseCase;
import com.adjt.pagamento.amqp.dto.event.ConsultaCriadaEvent;
import com.adjt.pagamento.amqp.dto.request.PagamentoRequest;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class MeioPagamentoService {

    private final AtualizarPagamentoSucessoUseCase atualizarPagamentoSucesso;
    private final AtualizarPagamentoErroUseCase atualizarPagamentoErro;
    private final RestTemplate restTemplate;

    public MeioPagamentoService(AtualizarPagamentoSucessoUseCase atualizarPagamentoSucesso,
                                AtualizarPagamentoErroUseCase atualizarPagamentoErro) {
        this.atualizarPagamentoSucesso = atualizarPagamentoSucesso;
        this.atualizarPagamentoErro = atualizarPagamentoErro;
        this.restTemplate = new RestTemplate();
    }

    /**
     * Tenta novamente se ocorrer erro de rede ou erro 5xx/408
     * maxAttempts: Tenta 3 vezes
     * backoff: Espera 5 segundos antes de tentar novamente, dobrando o tempo a cada falha
     */
    @Retryable(
            retryFor = { HttpServerErrorException.class, ResourceAccessException.class },
            maxAttempts = 5,
            backoff = @Backoff(delay = 5000, multiplier = 2.0)
    )
    public void enviarRequisicaoPagamento(ConsultaCriadaEvent event) {
        System.out.println("Tentando enviar pagamento ID: " + event.consultaId());

        PagamentoRequest pagamentoRequest = new PagamentoRequest(event.valor().intValue(),
                String.valueOf(event.consultaId()),
                String.valueOf(event.pacienteId()));

        // Chamada para a API da imagem
        String API_URL = "http://localhost:8089//requisicao";
        restTemplate.postForEntity(API_URL, pagamentoRequest, Void.class);

        // Atualizar o banco para "PROCESSADO"
        atualizarStatusSucesso(event.consultaId());
    }

    /**
     * Se todas as 3 tentativas falharem, este método é chamado.
     */
    @Recover
    public void recover(Exception e, ConsultaCriadaEvent request) {
        int codigoErro = 0; // Padrão para erro desconhecido ou rede

        // Se o erro for do tipo HTTP (tem código 408, 502, etc)
        if (e instanceof HttpStatusCodeException httpException) {
            codigoErro = httpException.getStatusCode().value();
        }
        else if (e instanceof ResourceAccessException) {
            codigoErro = 408;
        }

        System.err.println("Falha total. Salvando no banco com código: " + codigoErro);

        marcarParaReprocessamentoPosterior(request.consultaId(), codigoErro);
    }

    private void atualizarStatusSucesso(Integer id) {
        this.atualizarPagamentoSucesso.run(id);
    }

    private void marcarParaReprocessamentoPosterior(Integer id, int codigoErro) {
        this.atualizarPagamentoErro.run(id, codigoErro);
    }
}