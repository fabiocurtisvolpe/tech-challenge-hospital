package com.adjt.agendamento.core.port;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;

import java.util.List;

public interface EspecialidadePort<Especialidade> {

    Especialidade criar(Especialidade model);
    Especialidade atualizar(Especialidade model);
    Boolean excluir(Integer id);
    Especialidade obterPorId(Integer id);
    ResultadoPaginacaoDTO<Especialidade> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts);
}
