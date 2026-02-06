package com.adjt.pagamento.data.mapper;

import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.data.entity.PagamentoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
    Pagamento toModel(PagamentoEntity entity);
    PagamentoEntity toEntity(Pagamento model);
}
