package com.adjt.agendamento.data.repository.adapter;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Especialidade;
import com.adjt.agendamento.core.port.EspecialidadePort;
import com.adjt.agendamento.data.repository.jpa.EspecialidadeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EspacialidadeRepositoryAdapter implements EspecialidadePort<Especialidade> {

    private final EspecialidadeRepository especialidadeRepository;

    public EspacialidadeRepositoryAdapter(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    @Override
    public Especialidade criar(Especialidade entidade) {
        return null;
    }

    @Override
    public Especialidade atualizar(Especialidade entidade) {
        return null;
    }

    @Override
    public Boolean excluir(Especialidade entidade) {
        return null;
    }

    @Override
    public Optional<Especialidade> obterPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public ResultadoPaginacaoDTO<Especialidade> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts) {
        return null;
    }
}
