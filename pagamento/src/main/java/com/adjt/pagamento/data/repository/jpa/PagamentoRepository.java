package com.adjt.pagamento.data.repository.jpa;


import com.adjt.pagamento.data.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Integer> {

    @Query("SELECT p FROM PagamentoEntity p WHERE p.responseCode = :responseCode")
    List<PagamentoEntity> findByPagamentoResponseCode(Integer responseCode);
}
