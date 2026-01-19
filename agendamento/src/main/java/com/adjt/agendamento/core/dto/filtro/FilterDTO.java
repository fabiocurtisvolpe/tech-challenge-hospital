package com.adjt.agendamento.core.dto.filtro;

import com.adjt.agendamento.core.enums.FiltroOperadorEnum;
import lombok.Getter;

@Getter
public class FilterDTO {

    private final String campo;
    private final String valor;
    private final FiltroOperadorEnum operador;

    public FilterDTO(String campo, String valor, FiltroOperadorEnum operador) {
        this.campo = campo;
        this.valor = valor;
        this.operador = operador;
    }
}
