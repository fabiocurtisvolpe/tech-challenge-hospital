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
@Table(schema = "public", name = "tb_notificacao")
public class NotificacaoEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id", nullable = false)
    private ConsultaEntity consulta;

    @PrePersist
    protected void onCreate() {
        if (this.getDataCriacao() == null) {
            this.setDataCriacao(LocalDateTime.now());
        }
    }
}