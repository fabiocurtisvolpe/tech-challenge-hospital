package com.adjt.agendamento.amqp.service;

import com.adjt.agendamento.amqp.util.MessageSecurityUtil;
import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.usecase.consulta.ObterPorIdConsultaUseCase;
import com.adjt.agendamento.amqp.config.RabbitConfig;
import com.adjt.agendamento.amqp.event.ConsultaNotificacaoEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;

@Slf4j
@Service
public class NotificacaoService {

    private final RabbitTemplate rabbitTemplate;
    private final ObterPorIdConsultaUseCase obterConsultaUseCase;
    private final ObjectMapper objectMapper;
    private final RSAPrivateKey privateKey;

    public NotificacaoService(RabbitTemplate rabbitTemplate,
                              ObterPorIdConsultaUseCase obterConsultaUseCase,
                              ObjectMapper objectMapper,
                              RSAPrivateKey privateKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.obterConsultaUseCase = obterConsultaUseCase;
        this.objectMapper = objectMapper;
        this.privateKey = privateKey;
    }

    public void enviarNotificacao(Integer idConsulta) {
        Consulta consulta = this.obterConsultaUseCase.runNotificacao(idConsulta);
        if (consulta != null) {

            try {

                ConsultaNotificacaoEvent event = new ConsultaNotificacaoEvent(consulta.getId(),
                        consulta.getMedico().getNome(), consulta.getEspecialidade().getNome(), consulta.getDataHora());

                log.info("NotificacaoService enviarNotificacao: {}", event);

                String jsonPayload = objectMapper.writeValueAsString(event);
                String assinatura = MessageSecurityUtil.sign(jsonPayload, this.privateKey);

                rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NOTIFICACAO,
                        RabbitConfig.ROUTING_KEY_NOTIFICACAO_ENVIAR, event, message -> {
                            message.getMessageProperties().setHeader("X-Signature", assinatura);
                            message.getMessageProperties().setHeader("X-Service-Source", "ms-agendamento");
                            return message;
                        });

            } catch (Exception e) {
                throw new RuntimeException("Erro ao assinar mensagem para o RabbitMQ", e);
            }
        }
    }
}
