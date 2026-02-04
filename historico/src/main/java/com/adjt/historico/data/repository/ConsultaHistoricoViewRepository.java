package com.adjt.historico.data.repository;

import com.adjt.historico.data.entity.ConsultaAudId;
import com.adjt.historico.data.entity.ConsultaHistoricoView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaHistoricoViewRepository  extends JpaRepository<ConsultaHistoricoView, ConsultaAudId> {

    List<ConsultaHistoricoView> findAllByIdOrderByRevDesc(Integer id);
}