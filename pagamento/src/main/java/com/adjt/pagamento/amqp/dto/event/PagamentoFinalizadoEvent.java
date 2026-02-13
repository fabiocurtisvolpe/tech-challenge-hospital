package com.adjt.pagamento.amqp.dto.event;

import com.adjt.pagamento.core.enums.StatusPagamentoEnum;

public record PagamentoFinalizadoEvent(
        Integer consultaId,
        StatusPagamentoEnum status
) {}