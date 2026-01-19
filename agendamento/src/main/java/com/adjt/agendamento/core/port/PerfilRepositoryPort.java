package com.adjt.agendamento.core.port;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Perfil;

import java.util.List;
import java.util.Optional;

public interface PerfilRepositoryPort {

    Perfil criar(Perfil entidade);
    Perfil atualizar(Perfil entidade);
    Boolean excluir(Perfil entidade);

    Optional<Perfil> obterPorId(Integer id);

    ResultadoPaginacaoDTO<Perfil> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts);
}
