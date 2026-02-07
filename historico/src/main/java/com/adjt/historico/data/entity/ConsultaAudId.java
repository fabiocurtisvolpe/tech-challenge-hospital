package com.adjt.historico.data.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ConsultaAudId implements Serializable {
    private Integer id;
    private Integer rev;
}