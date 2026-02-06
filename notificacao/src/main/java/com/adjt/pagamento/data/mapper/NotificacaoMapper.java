package com.adjt.pagamento.data.mapper;

import com.adjt.pagamento.core.model.Notificacao;
import com.adjt.pagamento.data.entity.NotificacaoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificacaoMapper {
    Notificacao toModel(NotificacaoEntity entity);
    NotificacaoEntity toEntity(Notificacao model);

    List<Notificacao> toModelList(List<NotificacaoEntity> entities);
    List<NotificacaoEntity> toEntityList(List<Notificacao> models);
}
