package com.adjt.agendamento.rest.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UsuarioRequest {

    @Positive(message = "O ID deve ser um número positivo maior que zero")
    Integer id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    private String nome;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    @Pattern(
            regexp = "^\\(?[1-9]{2}\\)?\\s?9?[0-9]{4}[-\\s]?[0-9]{4}$",
            message = "Telefone inválido. Formatos aceitos: (11) 91234-5678, 11912345678 ou 1134567890"
    )
    private String telefone;

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "O e-mail deve ser válido")
    @Size(max = 50, message = "O e-mail deve ter no máximo 50 caracteres")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotEmpty(message = "O usuário deve ter pelo menos um perfil")
    private Set<Integer> perfisIds;
}
