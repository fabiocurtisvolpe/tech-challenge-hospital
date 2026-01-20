package com.adjt.agendamento.data.repository.jpa;

import com.adjt.agendamento.data.entity.NotificacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface NotificacaoRepository extends JpaRepository<NotificacaoEntity, Integer>,
        JpaSpecificationExecutor<NotificacaoEntity> {

    Optional<NotificacaoEntity> obterPorId(Integer id);
}
