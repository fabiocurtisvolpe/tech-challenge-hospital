package com.adjt.agendamento.data.repository.jpa;

import com.adjt.agendamento.data.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Integer>,
        JpaSpecificationExecutor<ConsultaEntity> {

    @Query("SELECT c FROM ConsultaEntity c WHERE c.medico.id = :medicoId " +
            "AND c.dataHora BETWEEN :inicio AND :fim")
    List<ConsultaEntity> findByMedicoAndIntervalo(Integer medicoId, LocalDateTime inicio, LocalDateTime fim);
}
