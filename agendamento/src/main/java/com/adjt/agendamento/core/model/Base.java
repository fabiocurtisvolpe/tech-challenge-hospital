package com.adjt.agendamento.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public abstract class Base implements Serializable {

    protected Integer id;
    protected LocalDateTime dataCriacao;
}
