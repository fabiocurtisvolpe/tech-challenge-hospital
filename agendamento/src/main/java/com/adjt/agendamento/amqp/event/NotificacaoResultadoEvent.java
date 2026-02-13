package com.adjt.agendamento.amqp.event;

public record NotificacaoResultadoEvent(Integer id, boolean resultado) {
}
