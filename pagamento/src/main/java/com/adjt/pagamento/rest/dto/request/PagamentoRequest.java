package com.adjt.pagamento.rest.dto.request;

import java.math.BigDecimal;

public record PagamentoRequest(BigDecimal valor, String pagamento_id, String cliente_id) {}