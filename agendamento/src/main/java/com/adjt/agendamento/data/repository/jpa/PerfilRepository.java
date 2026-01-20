package com.adjt.agendamento.data.repository.jpa;

import com.adjt.agendamento.data.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<PerfilRepository, Integer>,
        JpaSpecificationExecutor<PerfilEntity> {

    Optional<PerfilEntity> obterPorId(Integer id);
}
