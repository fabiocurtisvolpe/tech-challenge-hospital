package com.adjt.agendamento.amqp.service;

import com.adjt.agendamento.amqp.config.RabbitConfig;
import com.adjt.agendamento.amqp.event.ConsultaCriadaEvent;
import com.adjt.agendamento.amqp.util.MessageSecurityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.interfaces.RSAPrivateKey;

@Slf4j
@Service
public class PagamentoService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final RSAPrivateKey privateKey;

    public PagamentoService(RabbitTemplate rabbitTemplate,
                            ObjectMapper objectMapper,
                            RSAPrivateKey privateKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.privateKey = privateKey;
    }

    public void pagamento(Integer idConsulta, Integer idPaciente, BigDecimal valor) {
        try {

            ConsultaCriadaEvent event = new ConsultaCriadaEvent(idConsulta, idPaciente, valor);
            log.info("PagamentoService pagamento: {}", event);

            String jsonPayload = objectMapper.writeValueAsString(event);
            String assinatura = MessageSecurityUtil.sign(jsonPayload, this.privateKey);

            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_PAGAMENTO,
                    RabbitConfig.ROUTING_KEY_CONSULTA_CRIADA, event, message -> {
                message.getMessageProperties().setHeader("X-Signature", assinatura);
                message.getMessageProperties().setHeader("X-Service-Source", "ms-agendamento");
                return message;
            });

        } catch (Exception e) {
            throw new RuntimeException("Erro ao assinar mensagem para o RabbitMQ", e);
        }
    }
}
