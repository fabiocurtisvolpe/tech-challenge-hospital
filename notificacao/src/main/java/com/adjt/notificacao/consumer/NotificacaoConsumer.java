package com.adjt.notificacao.consumer;

import com.adjt.notificacao.config.RabbitConfig;
import com.adjt.notificacao.dto.NotificacaoEvent;
import com.adjt.notificacao.dto.NotificacaoResultadoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificacaoConsumer {

    private final RabbitTemplate rabbitTemplate;

    public NotificacaoConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NOTIFICACAO_PROCESSAR)
    public void receberConsultaParaNotificacao(NotificacaoEvent event)
    {
        NotificacaoResultadoEvent resultadoEvent = new NotificacaoResultadoEvent(event.getId(), true);
        log.info("receberConsultaParaNotificacao: {}", resultadoEvent);

        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_AGENDAMENTO,
                RabbitConfig.ROUTING_KEY_RESULTADO, resultadoEvent);
    }
}