package com.adjt.agendamento.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Audited
@Table(schema = "public", name = "tb_perfil")
public class PerfilEntity extends BaseEntity {

    @Column(name = "nome",  nullable = false, length = 20)
    private String nome;

    @PrePersist
    protected void onCreate() {
        if (this.getDataCriacao() == null) {
            this.setDataCriacao(LocalDateTime.now());
        }
    }
}
