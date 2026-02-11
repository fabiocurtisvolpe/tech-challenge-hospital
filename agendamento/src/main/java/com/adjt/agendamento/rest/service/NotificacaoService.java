package com.adjt.agendamento.rest.service;

import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.usecase.consulta.ObterPorIdConsultaUseCase;
import com.adjt.agendamento.rest.config.RabbitConfig;
import com.adjt.agendamento.rest.dto.event.ConsultaNotificacaoEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    private final RabbitTemplate rabbitTemplate;
    private final ObterPorIdConsultaUseCase obterConsultaUseCase;

    public NotificacaoService(RabbitTemplate rabbitTemplate, ObterPorIdConsultaUseCase obterConsultaUseCase) {
        this.rabbitTemplate = rabbitTemplate;
        this.obterConsultaUseCase = obterConsultaUseCase;
    }

    public void enviarNotificacao(Integer idConsulta) {
        Consulta consulta = this.obterConsultaUseCase.runNotificacao(idConsulta);
        if (consulta != null) {
            ConsultaNotificacaoEvent event = new ConsultaNotificacaoEvent(consulta.getId(),
                    consulta.getMedico().getNome(), consulta.getEspecialidade().getNome(), consulta.getDataHora());

            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NOTIFICACAO, RabbitConfig.ROUTING_KEY_NOTIFICACAO_ENVIAR, event);
        }
    }
}
