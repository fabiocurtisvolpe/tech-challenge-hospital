package com.adjt.agendamento.data.repository.adapter;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Perfil;
import com.adjt.agendamento.core.port.PerfilPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PerfilRepositoryAdapter implements PerfilPort<Perfil> {
    @Override
    public Perfil criar(Perfil entidade) {
        return null;
    }

    @Override
    public Perfil atualizar(Perfil entidade) {
        return null;
    }

    @Override
    public Boolean excluir(Perfil entidade) {
        return null;
    }

    @Override
    public Optional<Perfil> obterPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public ResultadoPaginacaoDTO<Perfil> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts) {
        return null;
    }
}
