package com.adjt.agendamento.data.mapper;

import com.adjt.agendamento.core.model.Especialidade;
import com.adjt.agendamento.data.entity.EspecialidadeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface EspecialidadeMapper {
    Especialidade toModel(EspecialidadeEntity entity);
    EspecialidadeEntity toEntity(Especialidade model);
}
