package com.adjt.notificacao.data.repository.jpa;

import com.adjt.notificacao.data.entity.NotificacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificacaoRepository extends JpaRepository<NotificacaoEntity, Integer>,
        JpaSpecificationExecutor<NotificacaoEntity> {
}
