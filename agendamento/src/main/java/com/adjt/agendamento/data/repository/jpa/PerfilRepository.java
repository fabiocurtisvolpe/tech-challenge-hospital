package com.adjt.agendamento.data.repository.jpa;

import com.adjt.agendamento.data.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Integer>,
        JpaSpecificationExecutor<PerfilEntity> {
}
