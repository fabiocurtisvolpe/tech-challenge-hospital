package com.adjt.agendamento.core.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.MINUTES;

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

    public boolean temConflitoHorario(Consulta outra) {
        if (this.dataHora == null || outra.getDataHora() == null) {
            return false;
        }

        long minutosDeDiferenca = Math.abs(MINUTES.between(this.dataHora, outra.getDataHora()));

        return minutosDeDiferenca < 30;
    }
}