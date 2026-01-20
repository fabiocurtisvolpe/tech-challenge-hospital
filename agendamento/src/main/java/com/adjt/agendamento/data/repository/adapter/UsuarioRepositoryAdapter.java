package com.adjt.agendamento.data.repository.adapter;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.data.repository.jpa.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryAdapter implements UsuarioPort<Usuario> {

    private final UsuarioRepository usuarioRepository;

    public UsuarioRepositoryAdapter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario criar(Usuario entidade) {
        return null;
    }

    @Override
    public Usuario atualizar(Usuario entidade) {
        return null;
    }

    @Override
    public Boolean excluir(Usuario entidade) {
        return null;
    }

    @Override
    public Optional<Usuario> obterPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> obterPorNome(String nome) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> obterPorEmail(String email) {
        return Optional.empty();
    }

    @Override
    public ResultadoPaginacaoDTO<Usuario> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts) {
        return null;
    }
}
