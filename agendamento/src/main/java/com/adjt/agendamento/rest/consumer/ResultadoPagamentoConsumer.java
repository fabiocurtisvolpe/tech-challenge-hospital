package com.adjt.agendamento.rest.consumer;

import com.adjt.agendamento.core.usecase.consulta.AtualizarStatusPagamentoConsultaUseCase;
import com.adjt.agendamento.rest.config.RabbitConfig;
import com.adjt.agendamento.rest.dto.event.PagamentoFinalizadoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResultadoPagamentoConsumer {
    private final AtualizarStatusPagamentoConsultaUseCase atualizarStatusPagamentoConsultaUseCase;

    public ResultadoPagamentoConsumer(AtualizarStatusPagamentoConsultaUseCase atualizarStatusPagamentoConsultaUseCase) {
        this.atualizarStatusPagamentoConsultaUseCase  = atualizarStatusPagamentoConsultaUseCase;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_AGENDAMENTO_ATUALIZAR)
    public void pagamentoStatusConsulta(PagamentoFinalizadoEvent event) {
        log.info("atualizarStatusConsulta: {}", event);
        this.atualizarStatusPagamentoConsultaUseCase.run(event.consultaId(), event.status());
    }
}
