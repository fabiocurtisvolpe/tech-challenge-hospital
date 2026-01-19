package com.adjt.agendamento.core.port;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Especialidade;

import java.util.List;
import java.util.Optional;

public interface EspecialidadeRepositoryPort {

    Especialidade criar(Especialidade entidade);
    Especialidade atualizar(Especialidade entidade);
    Boolean excluir(Especialidade entidade);

    Optional<Especialidade> obterPorId(Integer id);

    ResultadoPaginacaoDTO<Especialidade> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts);
}
