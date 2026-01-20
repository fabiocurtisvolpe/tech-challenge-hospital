package com.adjt.agendamento.data.mapper;

import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.data.entity.ConsultaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, EspecialidadeMapper.class})
public interface ConsultaMapper {
    Consulta toModel(ConsultaEntity entity);
    ConsultaEntity toEntity(Consulta model);
}
