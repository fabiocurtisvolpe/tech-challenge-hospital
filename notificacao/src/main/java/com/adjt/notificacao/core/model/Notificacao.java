package com.adjt.notificacao.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Notificacao {

    protected Integer id;
    protected LocalDateTime dataCriacao;
    protected LocalDateTime dataHora;
    protected String nomePaciente;
    protected String telefonePaciente;
    protected String emailPaciente;
    protected String nomeMedico;
    protected String especialidade;
}