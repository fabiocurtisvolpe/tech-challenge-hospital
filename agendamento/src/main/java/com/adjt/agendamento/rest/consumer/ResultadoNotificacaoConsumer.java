package com.adjt.agendamento.rest.consumer;

import com.adjt.agendamento.rest.config.RabbitConfig;
import com.adjt.agendamento.rest.dto.event.NotificacaoResultadoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResultadoNotificacaoConsumer {

    public ResultadoNotificacaoConsumer() {
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NOTIFICACAO_ATUALIZAR)
    public void notificacaoStatusConsulta(NotificacaoResultadoEvent event) {
        log.info("notificacaoStatusConsulta: {}", event);
    }
}
