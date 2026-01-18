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
@Table(schema = "public", name = "tb_usuario")
public class UsuarioEntity extends BaseEntity {

    @Column(name = "nome",  nullable = false, length = 50)
    private String nome;

    @Column(name = "email",  nullable = false, length = 50)
    private String email;

    @Column(name = "senha",  nullable = false)
    private String senha;

    @Column(name = "ativo",  nullable = false)
    private Boolean ativo;

    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinTable(
            name = "tb_usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private Set<PerfilEntity> perfis = new HashSet<>();

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