package com.adjt.agendamento.core.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class Consulta extends Base {
    
    protected LocalDateTime dataHora;
    protected String diagnostico;
    protected String prescricao;
    protected Usuario paciente;
    protected Usuario medico;
    protected Usuario enfermeiro;
    protected Especialidade especialidade;
}