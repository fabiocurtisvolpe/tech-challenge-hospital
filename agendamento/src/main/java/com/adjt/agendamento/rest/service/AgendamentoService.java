package com.adjt.agendamento.rest.service;

import com.adjt.agendamento.rest.config.RabbitConfig;
import com.adjt.agendamento.rest.dto.event.ConsultaCriadaEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AgendamentoService {

    private final RabbitTemplate rabbitTemplate;

    public AgendamentoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void pagamento(Integer idConsulta, Integer idPaciente, BigDecimal valor) {

        ConsultaCriadaEvent event = new ConsultaCriadaEvent(idConsulta, idPaciente, valor);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_CONSULTA, RabbitConfig.ROUTING_KEY_CONSULTA_CRIADA, event);
    }
}
