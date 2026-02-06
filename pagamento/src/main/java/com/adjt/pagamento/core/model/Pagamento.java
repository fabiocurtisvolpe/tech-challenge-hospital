package com.adjt.pagamento.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Pagamento implements Serializable {

    private Integer id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataHora;
    private Integer idConsulta;
    private Integer idPaciente;
    private BigDecimal valor;
    private Integer responseCode;
}
