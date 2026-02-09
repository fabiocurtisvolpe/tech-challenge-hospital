package com.adjt.agendamento.rest.consumer;

import com.adjt.agendamento.core.usecase.consulta.AtualizarStatusPagamentoConsultaUseCase;
import com.adjt.agendamento.rest.config.RabbitConfig;
import com.adjt.agendamento.rest.dto.event.PagamentoFinalizadoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ResultadoPagamentoConsumer {
    private final AtualizarStatusPagamentoConsultaUseCase atualizarStatusPagamentoConsultaUseCase;

    public ResultadoPagamentoConsumer(AtualizarStatusPagamentoConsultaUseCase atualizarStatusPagamentoConsultaUseCase) {
        this.atualizarStatusPagamentoConsultaUseCase  = atualizarStatusPagamentoConsultaUseCase;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_AGENDAMENTO_ATUALIZAR)
    public void atualizarStatusConsulta(PagamentoFinalizadoEvent event) {
        this.atualizarStatusPagamentoConsultaUseCase.run(event.consultaId(), event.status());
    }
}
