package com.adjt.agendamento.core.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Notificacao extends Base {

    protected Consulta consulta;
}