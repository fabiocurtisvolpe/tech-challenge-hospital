package com.adjt.agendamento.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Audited
@Table(schema = "public", name = "tb_consulta")
public class ConsultaEntity extends BaseEntity {

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "diagnostico", length = 4000)
    private String diagnostico;

    @Column(name = "prescricao", length = 4000)
    private String prescricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private UsuarioEntity paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    private UsuarioEntity medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfermeiro_id", nullable = false)
    private UsuarioEntity enfermeiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidade_id", nullable = false)
    private EspecialidadeEntity especialidade;

    @PrePersist
    protected void onCreate() {
        if (this.getDataCriacao() == null) {
            this.setDataCriacao(LocalDateTime.now());
        }
    }
}