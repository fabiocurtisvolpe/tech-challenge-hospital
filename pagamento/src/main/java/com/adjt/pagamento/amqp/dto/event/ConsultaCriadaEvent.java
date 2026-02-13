package com.adjt.pagamento.amqp.dto.event;

import java.math.BigDecimal;

public record ConsultaCriadaEvent(
        Integer consultaId,
        Integer pacienteId,
        BigDecimal valor
) {}
