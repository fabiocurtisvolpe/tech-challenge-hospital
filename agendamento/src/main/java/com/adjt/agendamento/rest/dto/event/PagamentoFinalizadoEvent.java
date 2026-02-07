package com.adjt.agendamento.rest.dto.event;

import com.adjt.agendamento.core.enums.StatusPagamentoEnum;

public record PagamentoFinalizadoEvent(
        Integer consultaId,
        StatusPagamentoEnum status
) {}