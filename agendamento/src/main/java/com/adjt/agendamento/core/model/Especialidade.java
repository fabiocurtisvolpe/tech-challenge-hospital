package com.adjt.agendamento.core.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
public class Especialidade extends Base {

    protected String nome;
    protected String descricao;
    protected Boolean ativo;
    protected Set<Usuario> medicos;
}