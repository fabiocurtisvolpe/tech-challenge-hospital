package com.adjt.agendamento.core.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@SuperBuilder
public class Usuario extends Base {

    private String nome;
    private String email;
    private String senha;
    private Boolean ativo;
    private Set<Perfil> perfis;



    public boolean isMedico() {
        return perfis.stream().anyMatch(perfil -> "ROLE_MEDICO".equals(perfil.getNome()));
    }

    public boolean isEnfermeiro() {
        return perfis.stream().anyMatch(perfil -> "ROLE_ENFERMEIRO".equals(perfil.getNome()));
    }

    public boolean isPaciente() {
        return perfis.stream().anyMatch(perfil -> "ROLE_PACIENTE".equals(perfil.getNome()));
    }

    public boolean isAdmin() {
        return perfis.stream().anyMatch(perfil -> "ROLE_ADMIN".equals(perfil.getNome()));
    }

    public boolean hasRole(String role) {
        return perfis != null && perfis.stream()
                .anyMatch(p -> role.equalsIgnoreCase(p.getNome()));
    }

    public boolean hasAnyRole(List<String> roles) {
        return roles.stream().noneMatch(this::hasRole);
    }
}