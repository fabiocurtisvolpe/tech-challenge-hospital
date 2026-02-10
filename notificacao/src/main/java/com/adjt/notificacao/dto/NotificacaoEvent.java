package com.adjt.notificacao.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificacaoEvent {

    private Integer id;
    private String medico;
    private String especialidade;
    private LocalDateTime dataHora;

    public String mensagem() {
        return String.format("A consulta com o(a) %s (%S) foi agendada para o dia %s", this.medico, this.especialidade, this.dataHora);
    }
}
