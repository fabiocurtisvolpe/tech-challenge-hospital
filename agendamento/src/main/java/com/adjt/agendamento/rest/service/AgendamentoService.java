package com.adjt.agendamento.rest.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    private final RabbitTemplate rabbitTemplate;

    public AgendamentoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    /*
    public void agendar(Consulta consulta) {
        // 1. Salva no banco com status aguardando pagamento
        consulta.setStatus(StatusConsulta.AGUARDANDO_PAGAMENTO);
        repository.save(consulta);

        // 2. Envia para a fila do RabbitMQ
        ConsultaCriadaEvent event = new ConsultaCriadaEvent(
                consulta.getId(), consulta.getPaciente().getId(), consulta.getValor()
        );
        rabbitTemplate.convertAndSend("exchange.consulta", "routing.consulta.criada", event);
    }

     */
}
