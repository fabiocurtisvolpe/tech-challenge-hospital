package com.adjt.agendamento.amqp.consumer;

import com.adjt.agendamento.amqp.config.RabbitConfig;
import com.adjt.agendamento.amqp.event.NotificacaoResultadoEvent;
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
