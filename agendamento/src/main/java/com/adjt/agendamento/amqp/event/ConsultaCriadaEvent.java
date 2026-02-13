package com.adjt.agendamento.amqp.event;

import java.math.BigDecimal;

public record ConsultaCriadaEvent(
        Integer consultaId,
        Integer pacienteId,
        BigDecimal valor
) {}
