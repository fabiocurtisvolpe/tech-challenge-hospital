package com.adjt.agendamento.amqp.event;

import java.time.LocalDateTime;

public record ConsultaNotificacaoEvent(Integer id, String medico, String especialidade, LocalDateTime dataHora) {
}
