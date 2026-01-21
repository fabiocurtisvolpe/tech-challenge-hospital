package com.adjt.agendamento.data.mapper;

public interface EntityMapper<E, D> {
    D toModel(E entity);
    E toEntity(D model);
}
