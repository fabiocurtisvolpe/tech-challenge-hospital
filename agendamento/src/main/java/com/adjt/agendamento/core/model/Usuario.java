package com.adjt.agendamento.core.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
public class Usuario extends Base {

    private String nome;
    private String email;
    private String senha;
    private Boolean ativo;
    private Set<Perfil> perfis;
}