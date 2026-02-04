package com.adjt.historico.data.repository;

import com.adjt.historico.data.entity.ConsultaAudId;
import com.adjt.historico.data.entity.ConsultaHistoricoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaHistoricoViewRepository extends JpaRepository<ConsultaHistoricoView, ConsultaAudId> {

    List<ConsultaHistoricoView> findAllByIdOrderByRevDesc(Integer id);

    @Query("SELECT v FROM ConsultaHistoricoView v ORDER BY v.rev DESC")
    List<ConsultaHistoricoView> findTop100AllOrderByRevDesc();
}