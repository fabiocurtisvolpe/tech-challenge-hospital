package com.adjt.agendamento.rest.mapper;

import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.rest.dto.request.UsuarioRequest;
import com.adjt.agendamento.rest.dto.response.UsuarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", implementationName = "UsuarioRestMapperImpl")
public interface UsuarioRestMapper {

    @Mapping(target = "perfis", ignore = true)
    @Mapping(target = "telefone", expression = "java(request.getTelefone() != null ? request.getTelefone().replaceAll(\"\\\\D\", \"\") : null)")
    Usuario toModel(UsuarioRequest request);

    UsuarioResponse toResponse(Usuario model);
}
