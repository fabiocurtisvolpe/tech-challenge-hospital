package com.adjt.pagamento.data.entity;

import com.adjt.pagamento.core.enums.StatusPagamentoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Audited
@Table(schema = "public", name = "tb_pagamento")
public class PagamentoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dt_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "consulta_id")
    private Integer idConsulta;

    @Column(name = "paciente_id")
    private Integer idPaciente;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "response_code")
    private Integer responseCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 25)
    private StatusPagamentoEnum status = StatusPagamentoEnum.PENDENTE_PAGAMENTO;

    @PrePersist
    protected void onCreate() {
        if (this.getDataCriacao() == null) {
            this.setDataCriacao(LocalDateTime.now());
        }
    }
}
