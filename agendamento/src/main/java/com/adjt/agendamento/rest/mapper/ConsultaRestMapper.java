package com.adjt.agendamento.rest.mapper;

import com.adjt.agendamento.core.dto.GenericBase;
import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.rest.dto.request.ConsultaRequest;
import com.adjt.agendamento.rest.dto.response.ConsultaResponse;
import com.adjt.agendamento.rest.dto.response.GenericResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationName = "ConsultaRestMapperImpl")
public interface ConsultaRestMapper {

    Consulta toModel(ConsultaRequest request);

    GenericResponse toGenericResponse(GenericBase model);

    ConsultaResponse toResponse(Consulta model);
}
