package com.adjt.pagamento.rest.dto.event;

import java.math.BigDecimal;

public record ConsultaCriadaEvent(
        Integer consultaId,
        Integer pacienteId,
        BigDecimal valor
) {}
