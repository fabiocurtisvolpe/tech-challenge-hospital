package com.adjt.agendamento.data.repository.adapter;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Especialidade;
import com.adjt.agendamento.core.port.EspecialidadePort;
import com.adjt.agendamento.core.util.MensagemUtil;
import com.adjt.agendamento.data.entity.EspecialidadeEntity;
import com.adjt.agendamento.data.mapper.EntityMapper;
import com.adjt.agendamento.data.mapper.EspecialidadeMapper;
import com.adjt.agendamento.data.repository.jpa.EspecialidadeRepository;
import com.adjt.agendamento.data.service.PaginadoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class EspacialidadeRepositoryAdapter implements EspecialidadePort<Especialidade> {

    private final EspecialidadeRepository especialidadeRepository;
    private final EspecialidadeMapper especialidadeMapper;

    public EspacialidadeRepositoryAdapter(EspecialidadeRepository especialidadeRepository,
                                          EspecialidadeMapper especialidadeMapper) {
        this.especialidadeRepository = especialidadeRepository;
        this.especialidadeMapper = especialidadeMapper;
    }

    @Override
    @Transactional
    public Especialidade criar(Especialidade model) {
        EspecialidadeEntity entity = especialidadeMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        EspecialidadeEntity savedEntity = especialidadeRepository.save(entity);
        return especialidadeMapper.toModel(savedEntity);
    }

    @Override
    @Transactional
    public Especialidade atualizar(Especialidade model) {
        EspecialidadeEntity entity = especialidadeMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        EspecialidadeEntity savedEntity = especialidadeRepository.save(entity);
        return especialidadeMapper.toModel(savedEntity);
    }

    @Override
    @Transactional
    public Boolean excluir(Integer id) {
        EspecialidadeEntity entity = especialidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.ESPECIALIDADE_NAO_ENCONTRADA));

        entity.setAtivo(Boolean.FALSE);
        especialidadeRepository.save(entity);
        return true;
    }

    @Override
    @Transactional
    public Especialidade obterPorId(Integer id) {
        EspecialidadeEntity entity = especialidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.ESPECIALIDADE_NAO_ENCONTRADA));

        return especialidadeMapper.toModel(entity);
    }

    @Override
    @Transactional
    public ResultadoPaginacaoDTO<Especialidade> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts) {
        PaginadoService<EspecialidadeEntity, Especialidade> paginadoService = new PaginadoService<>(
                especialidadeRepository,
                new EntityMapper<>() {
                    @Override
                    public Especialidade toModel(EspecialidadeEntity e) {
                        return especialidadeMapper.toModel(e);
                    }

                    @Override
                    public EspecialidadeEntity toEntity(Especialidade m) {
                        return especialidadeMapper.toEntity(m);
                    }
                });

        return paginadoService.listarPaginado(page, size, filters, sorts);
    }
}
