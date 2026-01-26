package com.adjt.agendamento.data.repository.adapter;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.port.ConsultaPort;
import com.adjt.agendamento.core.util.MensagemUtil;
import com.adjt.agendamento.data.entity.ConsultaEntity;
import com.adjt.agendamento.data.mapper.ConsultaMapper;
import com.adjt.agendamento.data.mapper.EntityMapper;
import com.adjt.agendamento.data.repository.jpa.ConsultaRepository;
import com.adjt.agendamento.data.service.PaginadoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ConsultaRepositoryAdapter implements ConsultaPort<Consulta> {

    private final ConsultaRepository consultaRepository;
    private final ConsultaMapper consultaMapper;

    public ConsultaRepositoryAdapter(ConsultaRepository consultaRepository,  ConsultaMapper consultaMapper) {
        this.consultaRepository = consultaRepository;
        this.consultaMapper = consultaMapper;
    }

    @Override
    @Transactional
    public Consulta criar(Consulta model) {

        ConsultaEntity entity = consultaMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        ConsultaEntity savedEntity = consultaRepository.save(entity);
        return consultaMapper.toModel(savedEntity);
    }

    @Override
    @Transactional
    public Consulta atualizar(Consulta model) {

        ConsultaEntity entity = consultaMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        ConsultaEntity savedEntity = consultaRepository.save(entity);
        return consultaMapper.toModel(savedEntity);
    }

    @Override
    @Transactional
    public Boolean excluir(Integer id) {

        ConsultaEntity entity = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.CONSULTA_NAO_ENCONTRADA));

        consultaRepository.delete(entity);
        return true;
    }

    @Override
    @Transactional
    public Consulta obterPorId(Integer id) {
        ConsultaEntity entity = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.CONSULTA_NAO_ENCONTRADA));

        return consultaMapper.toModel(entity);
    }

    @Override
    @Transactional
    public List<Consulta> listarPorMedicoEIntervalo(Integer medicoId, LocalDateTime inicio, LocalDateTime fim) {
        return consultaRepository.findByMedicoAndIntervalo(medicoId, inicio, fim).stream()
                        .map(consultaMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResultadoPaginacaoDTO<Consulta> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts) {

        PaginadoService<ConsultaEntity, Consulta> paginadoService = new PaginadoService<>(
                consultaRepository,
                new EntityMapper<>() {
                    @Override
                    public Consulta toModel(ConsultaEntity e) {
                        return consultaMapper.toModel(e);
                    }

                    @Override
                    public ConsultaEntity toEntity(Consulta m) {
                        return consultaMapper.toEntity(m);
                    }
                });

        return paginadoService.listarPaginado(page, size, filters, sorts);
    }
}
