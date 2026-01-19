package com.adjt.agendamento.core.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Perfil extends Base {

    protected String nome;
}
