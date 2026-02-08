package com.adjt.pagamento.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class PagamentoRequest implements Serializable {

    @JsonProperty("valor")
    private Integer valor;

    @JsonProperty("pagamento_id")
    private String idPagamento;

    @JsonProperty("cliente_id")
    private String idCliente;

    public PagamentoRequest(Integer valor, String idPagamento, String idCliente) {
        this.valor = valor;
        this.idPagamento = idPagamento;
        this.idCliente = idCliente;
    }
}