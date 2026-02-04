package com.adjt.historico.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import java.time.LocalDateTime;

@Getter
@Entity
@Immutable
@Table(name = "vw_consulta_historico", schema = "public")
@IdClass(ConsultaAudId.class)
public class ConsultaHistoricoView {

    @Id
    private Integer id;

    @Id
    private Integer rev;

    private Integer revtype;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    private String diagnostico;
    private String prescricao;

    @Column(name = "paciente_nome")
    private String pacienteNome;

    @Column(name = "medico_nome")
    private String medicoNome;

    @Column(name = "especialidade_nome")
    private String especialidadeNome;
}