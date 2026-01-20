package com.adjt.agendamento.core.port;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;

import java.util.List;
import java.util.Optional;

public interface ConsultaPort<Consulta> {

    Consulta criar(Consulta model);
    Consulta atualizar(Consulta model);
    Boolean excluir(Integer id);

    ResultadoPaginacaoDTO<Consulta> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts);
}
