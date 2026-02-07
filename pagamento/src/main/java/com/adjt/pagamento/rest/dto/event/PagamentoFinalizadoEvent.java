package com.adjt.pagamento.rest.dto.event;

import com.adjt.pagamento.core.enums.StatusPagamentoEnum;

public record PagamentoFinalizadoEvent(
        Integer consultaId,
        StatusPagamentoEnum status
) {}