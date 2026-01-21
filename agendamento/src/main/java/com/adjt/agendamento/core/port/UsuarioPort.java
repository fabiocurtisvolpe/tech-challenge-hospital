package com.adjt.agendamento.core.port;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;

import java.util.List;

public interface UsuarioPort<Usuario> {

    Usuario criar(Usuario model);
    Usuario atualizar(Usuario model);
    Boolean excluir(Integer id);
    Usuario obterPorEmail(String email);

    ResultadoPaginacaoDTO<Usuario> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts);
}
