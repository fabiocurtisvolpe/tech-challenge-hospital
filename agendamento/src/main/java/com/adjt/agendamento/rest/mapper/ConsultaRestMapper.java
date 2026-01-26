package com.adjt.agendamento.rest.mapper;

import com.adjt.agendamento.core.dto.GenericBase;
import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.rest.dto.request.ConsultaRequest;
import com.adjt.agendamento.rest.dto.request.UsuarioRequest;
import com.adjt.agendamento.rest.dto.response.ConsultaResponse;
import com.adjt.agendamento.rest.dto.response.GenericResponse;
import com.adjt.agendamento.rest.dto.response.UsuarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", implementationName = "ConsultaRestMapperImpl")
public interface ConsultaRestMapper {

    Consulta toModel(ConsultaRequest request);

    GenericResponse toGenericResponse(GenericBase model);

    ConsultaResponse toResponse(Consulta model);
}
