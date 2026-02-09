package com.adjt.historico.rest.controller;

import com.adjt.historico.data.entity.ConsultaHistoricoView;
import com.adjt.historico.data.repository.ConsultaHistoricoViewRepository;
import com.adjt.historico.rest.dto.ConsultaFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ConsultaHistoricoGraphQLController {

    private final ConsultaHistoricoViewRepository repository;

    public ConsultaHistoricoGraphQLController(ConsultaHistoricoViewRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<ConsultaHistoricoView> historicoConsulta(@Argument ConsultaFilter filter) {

        if (filter == null) {
            return repository.findTop100ByOrderByRevDesc();
        }

        Specification<ConsultaHistoricoView> spec = (root, _, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getId() != null)
                predicates.add(cb.equal(root.get("id"), filter.getId()));

            if (filter.getStatus() != null)
                predicates.add(cb.equal(root.get("status"), filter.getStatus()));

            if (filter.getPacienteNome() != null)
                predicates.add(cb.like(cb.lower(root.get("pacienteNome")), "%" + filter.getPacienteNome().toLowerCase() + "%"));

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return repository.findAll(spec, Sort.by(Sort.Direction.DESC, "rev"));

    }
}
