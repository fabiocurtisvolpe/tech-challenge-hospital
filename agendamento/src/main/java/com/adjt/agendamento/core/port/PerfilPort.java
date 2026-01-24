package com.adjt.agendamento.core.port;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;

import java.util.List;
import java.util.Set;

public interface PerfilPort<Perfil> {

    Perfil criar(Perfil model);
    Perfil atualizar(Perfil model);
    Boolean excluir(Integer id);
    Perfil obterPorId(Integer id);
    Set<Perfil> buscarPorIds(Set<Integer> ids);
    ResultadoPaginacaoDTO<Perfil> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts);
}
