package com.adjt.notificacao.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Audited
@Table(schema = "public", name = "tb_notificacao")
public class NotificacaoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dt_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "nome_paciente",  nullable = false, length = 50)
    private String nomePaciente;

    @Column(name = "telefone_paciente", nullable = false, length = 20)
    private String telefonePaciente;

    @Column(name = "email_paciente",  nullable = false, length = 50)
    private String emailPaciente;

    @Column(name = "nome_medico",  nullable = false, length = 50)
    private String nomeMedico;

    @Column(name = "especialidade",  nullable = false, length = 100)
    private String especialidade;

    @PrePersist
    protected void onCreate() {
        if (this.getDataCriacao() == null) {
            this.setDataCriacao(LocalDateTime.now());
        }
    }
}