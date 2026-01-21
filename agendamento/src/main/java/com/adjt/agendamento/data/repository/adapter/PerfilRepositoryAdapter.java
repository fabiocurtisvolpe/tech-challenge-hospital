package com.adjt.agendamento.data.repository.adapter;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Perfil;
import com.adjt.agendamento.core.port.PerfilPort;
import com.adjt.agendamento.core.util.MensagemUtil;
import com.adjt.agendamento.data.entity.PerfilEntity;
import com.adjt.agendamento.data.mapper.EntityMapper;
import com.adjt.agendamento.data.mapper.PerfilMapper;
import com.adjt.agendamento.data.repository.jpa.PerfilRepository;
import com.adjt.agendamento.data.service.PaginadoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class PerfilRepositoryAdapter implements PerfilPort<Perfil> {

    private final PerfilRepository perfilRepository;
    private final PerfilMapper perfilMapper;

    public PerfilRepositoryAdapter(PerfilRepository perfilRepository,
                                   PerfilMapper perfilMapper) {
        this.perfilRepository = perfilRepository;
        this.perfilMapper = perfilMapper;
    }

    @Override
    @Transactional
    public Perfil criar(Perfil model) {
        PerfilEntity entity = perfilMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        PerfilEntity savedEntity = perfilRepository.save(entity);
        return perfilMapper.toModel(savedEntity);
    }

    @Override
    @Transactional
    public Perfil atualizar(Perfil model) {
        PerfilEntity entity = perfilMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        PerfilEntity savedEntity = perfilRepository.save(entity);
        return perfilMapper.toModel(savedEntity);
    }

    @Override
    public Boolean excluir(Integer id) {
        PerfilEntity entity = perfilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.PERFIL_NAO_ENCONTRADO));

        perfilRepository.delete(entity);
        return true;
    }

    @Transactional
    public Perfil obterPorId(Integer id) {
        PerfilEntity entity = perfilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.PERFIL_NAO_ENCONTRADO));

        return perfilMapper.toModel(entity);
    }

    @Override
    public ResultadoPaginacaoDTO<Perfil> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts) {
        PaginadoService<PerfilEntity, Perfil> paginadoService = new PaginadoService<>(
                perfilRepository,
                new EntityMapper<>() {
                    @Override
                    public Perfil toModel(PerfilEntity e) {
                        return perfilMapper.toModel(e);
                    }

                    @Override
                    public PerfilEntity toEntity(Perfil m) {
                        return perfilMapper.toEntity(m);
                    }
                });

        return paginadoService.listarPaginado(page, size, filters, sorts);
    }
}
