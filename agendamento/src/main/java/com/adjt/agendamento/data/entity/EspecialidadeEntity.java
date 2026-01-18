package com.adjt.agendamento.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Audited
@Table(schema = "public", name = "tb_especialidade")
public class EspecialidadeEntity extends BaseEntity {

    @Column(name = "nome",  nullable = false, length = 50)
    private String nome;

    @Column(name = "descricao",  nullable = false, length = 50)
    private String descricao;

    @Column(name = "ativo",  nullable = false)
    private Boolean ativo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_medico_especialidade",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    private Set<UsuarioEntity> medicos = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        if (this.getDataCriacao() == null) {
            this.setDataCriacao(LocalDateTime.now());
        }

        if (this.getAtivo() == null) {
            this.setAtivo(Boolean.TRUE);
        }
    }
}