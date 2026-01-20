package com.adjt.agendamento.data.repository.jpa;

import com.adjt.agendamento.data.entity.EspecialidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EspacialidadeRepository extends JpaRepository<EspecialidadeEntity, Integer>,
        JpaSpecificationExecutor<EspecialidadeEntity> {

    Optional<EspecialidadeEntity> obterPorId(Integer id);
}
