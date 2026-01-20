package com.adjt.agendamento.data.repository.adapter;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.port.ConsultaPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsultaRepositoryAdapter implements ConsultaPort<Consulta> {
    @Override
    public Consulta criar(Consulta entidade) {
        return null;
    }

    @Override
    public Consulta atualizar(Consulta entidade) {
        return null;
    }

    @Override
    public Boolean excluir(Consulta entidade) {
        return null;
    }

    @Override
    public Optional<Consulta> obterPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public ResultadoPaginacaoDTO<Consulta> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts) {
        return null;
    }
}
