package com.adjt.pagamento.rest.consumer;

import com.adjt.pagamento.core.enums.StatusPagamentoEnum;
import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.usecase.CadastrarPagamentoUseCase;
import com.adjt.pagamento.rest.config.RabbitConfig;
import com.adjt.pagamento.rest.dto.event.ConsultaCriadaEvent;
import com.adjt.pagamento.rest.dto.event.PagamentoFinalizadoEvent;
import com.adjt.pagamento.rest.service.MeioPagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PagamentoConsumer {

    private final CadastrarPagamentoUseCase cadastrarPagamentoUseCase;

    private final MeioPagamentoService meioPagamentoService;
    private final RabbitTemplate rabbitTemplate;

    public PagamentoConsumer(CadastrarPagamentoUseCase cadastrarPagamentoUseCase,
                             MeioPagamentoService meioPagamentoService,
                             RabbitTemplate rabbitTemplate) {

        this.cadastrarPagamentoUseCase = cadastrarPagamentoUseCase;
        this.meioPagamentoService = meioPagamentoService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_PAGAMENTO_PROCESSAR)
    public void receberConsultaParaPagar(ConsultaCriadaEvent event) {
        try {

            Pagamento pagamento = Pagamento.builder()
                    .idConsulta(event.consultaId())
                    .idPaciente(event.pacienteId())
                    .valor(event.valor())
                    .build();

            this.cadastrarPagamentoUseCase.run(pagamento);

            log.info("receberConsultaParaPagar: {}", event);

            meioPagamentoService.enviarRequisicaoPagamento(event);
            notificarAgendamento(event.consultaId(), StatusPagamentoEnum.APROVADO_PAGAMENTO);

        } catch (Exception e) {
            notificarAgendamento(event.consultaId(), StatusPagamentoEnum.REPROVADO_PAGAMENTO);
        }
    }

    private void notificarAgendamento(Integer id, StatusPagamentoEnum status) {
        var resultado = new PagamentoFinalizadoEvent(id, status);
        log.info("notificarAgendamento: {}", resultado);

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_AGENDAMENTO, RabbitConfig.ROUTING_KEY_RESULTADO, resultado);
    }
}
