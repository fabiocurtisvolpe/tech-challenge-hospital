package com.adjt.agendamento.amqp.event;

import com.adjt.agendamento.core.enums.StatusPagamentoEnum;

public record PagamentoFinalizadoEvent(
        Integer consultaId,
        StatusPagamentoEnum status
) {}