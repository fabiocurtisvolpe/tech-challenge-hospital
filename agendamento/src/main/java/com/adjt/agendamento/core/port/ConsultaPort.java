package com.adjt.agendamento.core.port;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.data.entity.ConsultaEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaPort<Consulta> {

    Consulta criar(Consulta model);
    Consulta atualizar(Consulta model);
    Boolean excluir(Integer id);
    Consulta obterPorId(Integer id);
    List<Consulta> listarPorMedicoEIntervalo(Integer medicoId, LocalDateTime inicio, LocalDateTime fim);
    ResultadoPaginacaoDTO<Consulta> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts);
}
