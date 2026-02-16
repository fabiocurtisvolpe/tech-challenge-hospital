package com.adjt.pagamento.amqp.consumer;

import com.adjt.pagamento.amqp.util.MessageSecurityUtil;
import com.adjt.pagamento.core.enums.StatusPagamentoEnum;
import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.usecase.CadastrarPagamentoUseCase;
import com.adjt.pagamento.amqp.config.RabbitConfig;
import com.adjt.pagamento.amqp.dto.event.ConsultaCriadaEvent;
import com.adjt.pagamento.amqp.dto.event.PagamentoFinalizadoEvent;
import com.adjt.pagamento.amqp.service.MeioPagamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;

@Slf4j
@Component
public class PagamentoConsumer {

    private final CadastrarPagamentoUseCase cadastrarPagamentoUseCase;

    private final ObjectMapper objectMapper;
    private final MeioPagamentoService meioPagamentoService;
    private final RabbitTemplate rabbitTemplate;
    private final RSAPublicKey publicKey;

    public PagamentoConsumer(CadastrarPagamentoUseCase cadastrarPagamentoUseCase,
                             ObjectMapper objectMapper,
                             MeioPagamentoService meioPagamentoService,
                             RabbitTemplate rabbitTemplate,
                             RSAPublicKey publicKey) {

        this.cadastrarPagamentoUseCase = cadastrarPagamentoUseCase;
        this.objectMapper = objectMapper;
        this.meioPagamentoService = meioPagamentoService;
        this.rabbitTemplate = rabbitTemplate;
        this.publicKey = publicKey;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_PAGAMENTO_PROCESSAR)
    public void receberConsultaParaPagar(ConsultaCriadaEvent event, @Header("X-Signature") String signature) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(event);
            boolean isValida = MessageSecurityUtil.verify(jsonPayload, signature, this.publicKey);

            if (!isValida) {
                log.error("ALERTA DE SEGURANÇA: Mensagem rejeitada! Assinatura inválida.");
                return;
            }

            log.info("Mensagem verificada com sucesso! Processando pagamento...");

            Pagamento pagamento = Pagamento.builder()
                    .idConsulta(event.consultaId())
                    .idPaciente(event.pacienteId())
                    .valor(event.valor())
                    .build();

            this.cadastrarPagamentoUseCase.run(pagamento);

            log.info("receberConsultaParaPagar: {}", event);

            meioPagamentoService.enviarRequisicaoPagamento(event);
            notificarAgendamento(event.consultaId());

        } catch (Exception e) {
            log.error("Erro técnico ao validar segurança da mensagem: {}", e.getMessage());
        }
    }

    private void notificarAgendamento(Integer id) {
        if (id != null) {
            var resultado = new PagamentoFinalizadoEvent(id, StatusPagamentoEnum.APROVADO_PAGAMENTO);
            log.info("notificarAgendamento: {}", resultado);

            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_AGENDAMENTO, RabbitConfig.ROUTING_KEY_RESULTADO, resultado);
        }
    }
}
