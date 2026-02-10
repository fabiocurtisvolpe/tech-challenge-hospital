package com.adjt.agendamento.rest.consumer;

import com.adjt.agendamento.core.usecase.consulta.AtualizarStatusPagamentoConsultaUseCase;
import com.adjt.agendamento.rest.config.RabbitConfig;
import com.adjt.agendamento.rest.dto.event.PagamentoFinalizadoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ResultadoNotificacaoConsumer {

    public ResultadoNotificacaoConsumer() {
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NOTIFICACAO_ATUALIZAR)
    public void atualizarStatusConsulta(PagamentoFinalizadoEvent event) {

    }
}
