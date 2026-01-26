package com.adjt.agendamento.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GenericResponse implements Serializable {

    private Integer id;
    private String nome;
}
