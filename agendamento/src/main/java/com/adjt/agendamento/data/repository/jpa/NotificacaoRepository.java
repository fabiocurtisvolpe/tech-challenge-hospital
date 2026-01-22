package com.adjt.agendamento.data.repository.jpa;

import com.adjt.agendamento.data.entity.NotificacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificacaoRepository extends JpaRepository<NotificacaoEntity, Integer>,
        JpaSpecificationExecutor<NotificacaoEntity> {
}
