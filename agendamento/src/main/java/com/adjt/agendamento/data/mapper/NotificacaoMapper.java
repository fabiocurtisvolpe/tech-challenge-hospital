package com.adjt.agendamento.data.mapper;

import com.adjt.agendamento.core.model.Notificacao;
import com.adjt.agendamento.data.entity.NotificacaoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ConsultaMapper.class})
public interface NotificacaoMapper {
    Notificacao toModel(NotificacaoEntity entity);
    NotificacaoEntity toEntity(Notificacao model);
}