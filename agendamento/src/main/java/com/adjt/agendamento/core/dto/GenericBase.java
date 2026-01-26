package com.adjt.agendamento.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GenericBase implements Serializable {

    private Integer id;
    private String nome;
}
