package com.adjt.agendamento.data.mapper;

import com.adjt.agendamento.core.model.Perfil;
import com.adjt.agendamento.data.entity.PerfilEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PerfilMapper {
    Perfil toModel(PerfilEntity entity);
    PerfilEntity toEntity(Perfil model);
}