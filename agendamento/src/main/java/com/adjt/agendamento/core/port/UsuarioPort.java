package com.adjt.agendamento.core.port;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioPort<Usuario> {

    Usuario criar(Usuario entidade);
    Usuario atualizar(Usuario entidade);
    Boolean excluir(Usuario entidade);

    Optional<Usuario> obterPorId(Integer id);
    Optional<Usuario> obterPorNome(String nome);
    Optional<Usuario> obterPorEmail(String email);

    ResultadoPaginacaoDTO<Usuario> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts);
}
