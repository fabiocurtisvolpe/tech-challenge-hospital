package com.adjt.agendamento.rest.dto.event;

import java.time.LocalDateTime;

public record ConsultaNotificacaoEvent(Integer id, String medico, String especialidade, LocalDateTime dataHora) {
}
