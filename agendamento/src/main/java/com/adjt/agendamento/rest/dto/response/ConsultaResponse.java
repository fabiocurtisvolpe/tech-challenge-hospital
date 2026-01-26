package com.adjt.agendamento.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ConsultaResponse implements Serializable {

    private Integer id;
    private LocalDateTime dataHora;
    private String diagnostico;
    private String prescricao;
    private UsuarioResponse paciente;
    private Integer medicoId;
    private Integer enfermeiroId;
    private Integer especialidadeId;
}
