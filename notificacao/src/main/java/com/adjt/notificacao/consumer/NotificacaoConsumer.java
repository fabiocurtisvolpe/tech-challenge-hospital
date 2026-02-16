package com.adjt.notificacao.consumer;

import com.adjt.notificacao.config.RabbitConfig;
import com.adjt.notificacao.dto.NotificacaoEvent;
import com.adjt.notificacao.dto.NotificacaoResultadoEvent;
import com.adjt.notificacao.util.MessageSecurityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;

@Slf4j
@Component
public class NotificacaoConsumer {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final RSAPublicKey publicKey;

    public NotificacaoConsumer(RabbitTemplate rabbitTemplate,
                               ObjectMapper objectMapper,
                               RSAPublicKey publicKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.publicKey = publicKey;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NOTIFICACAO_PROCESSAR)
    public void receberConsultaParaNotificacao(NotificacaoEvent event, @Header("X-Signature") String signature)
    {
        try {
            String jsonPayload = objectMapper.writeValueAsString(event);
            boolean isValida = MessageSecurityUtil.verify(jsonPayload, signature, this.publicKey);

            if (!isValida) {
                log.error("ALERTA DE SEGURANÇA: Mensagem rejeitada! Assinatura inválida.");
                return;
            }

            log.info("Mensagem verificada com sucesso! Processando pagamento...");

            NotificacaoResultadoEvent resultadoEvent = new NotificacaoResultadoEvent(event.getId(), true);
            log.info("receberConsultaParaNotificacao: {}", resultadoEvent);

            this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_AGENDAMENTO,
                    RabbitConfig.ROUTING_KEY_RESULTADO, resultadoEvent);

        } catch (Exception e) {
            log.error("Erro técnico ao validar segurança da mensagem: {}", e.getMessage());
        }
    }
}