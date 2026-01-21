package com.adjt.agendamento.data.mapper;

import com.adjt.agendamento.core.model.Notificacao;
import com.adjt.agendamento.data.entity.NotificacaoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ConsultaMapper.class})
public interface NotificacaoMapper {
    Notificacao toModel(NotificacaoEntity entity);
    NotificacaoEntity toEntity(Notificacao model);

    List<Notificacao> toModelList(List<NotificacaoEntity> entities);
    List<NotificacaoEntity> toEntityList(List<Notificacao> models);
}