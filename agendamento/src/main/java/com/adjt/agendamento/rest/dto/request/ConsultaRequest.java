package com.adjt.agendamento.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConsultaRequest {

    @Positive(message = "O ID deve ser um número positivo maior que zero")
    private Integer id;

    @NotNull(message = "A data e hora não pode estar em branco")
    private LocalDateTime dataHora;

    @Size(max = 4000, message = "O diagnóstico deve ter no máximo 4000 caracteres")
    private String diagnostico;

    @Size(max = 20, message = "A prescrição deve ter no máximo 4000 caracteres")
    private String prescricao;

    @Positive(message = "O ID paciente deve ser um número positivo maior que zero")
    @NotNull(message = "O ID paciente não pode estar em branco")
    private Integer pacienteId;

    @Positive(message = "O ID médico deve ser um número positivo maior que zero")
    @NotNull(message = "O ID médico não pode estar em branco")
    private Integer medicadoId;

    @Positive(message = "O ID enfermeiro deve ser um número positivo maior que zero")
    private Integer enfermeiroId;

    @Positive(message = "O ID especialidade deve ser um número positivo maior que zero")
    @NotNull(message = "O ID especialidade não pode estar em branco")
    private Integer especialidadeId;
}
