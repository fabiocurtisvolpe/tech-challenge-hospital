package com.adjt.agendamento.core.port;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Consulta;

import java.util.List;
import java.util.Optional;

public interface ConsultaRepositoryPort {

    Consulta criar(Consulta entidade);
    Consulta atualizar(Consulta entidade);
    Boolean excluir(Consulta entidade);

    Optional<Consulta> obterPorId(Integer id);

    ResultadoPaginacaoDTO<Consulta> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts);
}
