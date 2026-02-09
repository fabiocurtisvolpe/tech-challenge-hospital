package com.adjt.historico.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaFilter {

    private Integer id;
    private String status;
    private String pacienteNome;
    private String medicoNome;
    private String dataInicio;
    private String dataFim;
}
