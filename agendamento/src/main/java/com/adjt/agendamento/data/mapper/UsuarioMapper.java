package com.adjt.agendamento.data.mapper;

import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.data.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PerfilMapper.class})
public interface UsuarioMapper {
    Usuario toModel(UsuarioEntity entity);
    UsuarioEntity toEntity(Usuario model);
}
