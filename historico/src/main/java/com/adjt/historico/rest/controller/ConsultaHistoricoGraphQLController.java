package com.adjt.historico.rest.controller;

import com.adjt.historico.data.entity.ConsultaHistoricoView;
import com.adjt.historico.data.repository.ConsultaHistoricoViewRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/historico-consulta")
public class ConsultaHistoricoGraphQLController {

    private final ConsultaHistoricoViewRepository repository;

    public ConsultaHistoricoGraphQLController(ConsultaHistoricoViewRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<ConsultaHistoricoView> historicoConsulta(@Argument Integer id) {
        if (id != null) {
            return repository.findAllByIdOrderByRevDesc(id);
        } else {
            return repository.findTop100AllOrderByRevDesc();
        }
    }
}
