package com.adjt.pagamento.rest.dto.request;

public record PagamentoRequest(Double valor, String pagamento_id, String cliente_id) {}
