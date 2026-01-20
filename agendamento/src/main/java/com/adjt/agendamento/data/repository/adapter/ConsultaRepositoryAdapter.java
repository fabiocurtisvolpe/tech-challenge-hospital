package com.adjt.agendamento.data.repository.adapter;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.port.ConsultaPort;
import com.adjt.agendamento.core.util.MensagemUtil;
import com.adjt.agendamento.data.entity.ConsultaEntity;
import com.adjt.agendamento.data.mapper.ConsultaMapper;
import com.adjt.agendamento.data.repository.jpa.ConsultaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Consulta atualizar(Consulta entidade) {
        return null;
    }

    @Override
    @Transactional
    public Boolean excluir(Consulta entidade) {
        return null;
    }

    @Override
    @Transactional
    public Optional<Consulta> obterPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public ResultadoPaginacaoDTO<Consulta> listarPaginado(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts) {
        return null;
    }
}
