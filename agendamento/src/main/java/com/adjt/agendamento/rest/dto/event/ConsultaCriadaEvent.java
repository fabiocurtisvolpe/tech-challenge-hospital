package com.adjt.agendamento.rest.dto.event;

import java.math.BigDecimal;

public record ConsultaCriadaEvent(
        Integer consultaId,
        Integer pacienteId,
        BigDecimal valor
) {}
