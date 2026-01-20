package com.adjt.agendamento.data.repository.jpa;

import com.adjt.agendamento.data.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Integer>,
        JpaSpecificationExecutor<ConsultaEntity> {

    Optional<ConsultaEntity> obterPorId(Integer id);
}
