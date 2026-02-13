package com.adjt.agendamento.amqp.service;

import com.adjt.agendamento.amqp.config.RabbitConfig;
import com.adjt.agendamento.amqp.event.ConsultaCriadaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class PagamentoService {

    private final RabbitTemplate rabbitTemplate;

    public PagamentoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void pagamento(Integer idConsulta, Integer idPaciente, BigDecimal valor) {

        ConsultaCriadaEvent event = new ConsultaCriadaEvent(idConsulta, idPaciente, valor);
        log.info("PagamentoService pagamento: {}", event);

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_PAGAMENTO, RabbitConfig.ROUTING_KEY_CONSULTA_CRIADA, event);
    }
}
