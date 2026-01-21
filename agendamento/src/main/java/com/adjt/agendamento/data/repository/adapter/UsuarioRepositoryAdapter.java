package com.adjt.agendamento.data.repository.adapter;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.MensagemUtil;
import com.adjt.agendamento.data.entity.UsuarioEntity;
import com.adjt.agendamento.data.mapper.EntityMapper;
import com.adjt.agendamento.data.mapper.UsuarioMapper;
import com.adjt.agendamento.data.repository.jpa.UsuarioRepository;
import com.adjt.agendamento.data.service.PaginadoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class UsuarioRepositoryAdapter implements UsuarioPort<Usuario> {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioRepositoryAdapter(UsuarioRepository usuarioRepository,
                                    UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    @Transactional
    public Usuario criar(Usuario model) {
        UsuarioEntity entity = usuarioMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        UsuarioEntity savedEntity = usuarioRepository.save(entity);
        return usuarioMapper.toModel(savedEntity);
    }

    @Override
    @Transactional
    public Usuario atualizar(Usuario model) {
        UsuarioEntity entity = usuarioMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        UsuarioEntity savedEntity = usuarioRepository.save(entity);
        return usuarioMapper.toModel(savedEntity);
    }

    @Override
    @Transactional
    public Boolean excluir(Integer id) {

        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.USUARIO_NAO_ENCONTRADO));

        entity.setAtivo(Boolean.FALSE);
        usuarioRepository.save(entity);
        return true;
    }

    @Override
    @Transactional
    public Usuario obterPorId(Integer id) {
        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.USUARIO_NAO_ENCONTRADO));
        return usuarioMapper.toModel(entity);
    }

    @Override
    @Transactional
    public Usuario obterPorEmail(String email) {
        UsuarioEntity entity = usuarioRepository.findByEmailAndAtivoTrue(email)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.USUARIO_NAO_ENCONTRADO));

        return usuarioMapper.toModel(entity);
    }

    @Override
    @Transactional
    public ResultadoPaginacaoDTO<Usuario> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts) {
        PaginadoService<UsuarioEntity, Usuario> paginadoService = new PaginadoService<>(
                usuarioRepository,
                new EntityMapper<>() {
                    @Override
                    public Usuario toModel(UsuarioEntity e) {
                        return usuarioMapper.toModel(e);
                    }

                    @Override
                    public UsuarioEntity toEntity(Usuario m) {
                        return usuarioMapper.toEntity(m);
                    }
                });

        return paginadoService.listarPaginado(page, size, filters, sorts);
    }
}
