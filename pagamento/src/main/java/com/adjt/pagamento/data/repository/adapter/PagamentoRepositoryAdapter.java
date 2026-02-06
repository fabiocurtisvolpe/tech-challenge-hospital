package com.adjt.pagamento.data.repository.adapter;

import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.port.PagamentoPort;
import com.adjt.pagamento.core.util.MensagemUtil;
import com.adjt.pagamento.data.entity.PagamentoEntity;
import com.adjt.pagamento.data.mapper.PagamentoMapper;
import com.adjt.pagamento.data.repository.jpa.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class PagamentoRepositoryAdapter implements PagamentoPort<Pagamento> {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;

    public PagamentoRepositoryAdapter(PagamentoRepository pagamentoRepository, PagamentoMapper pagamentoMapper) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoMapper = pagamentoMapper;
    }

    @Override
    @Transactional
    public Pagamento criar(Pagamento model) {

        PagamentoEntity entity = pagamentoMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        PagamentoEntity saved = pagamentoRepository.save(entity);
        return pagamentoMapper.toModel(saved);
    }

    @Override
    @Transactional
    public Pagamento atualizar(Pagamento model) {

        PagamentoEntity entity = pagamentoMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        PagamentoEntity saved = pagamentoRepository.save(entity);
        return pagamentoMapper.toModel(saved);
    }

    @Override
    @Transactional
    public Pagamento obterPorContultaId(Integer id) {
        PagamentoEntity entity = pagamentoRepository.findByConsultaId(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.PGTO_NAO_ENCONTRADO));
        return this.pagamentoMapper.toModel(entity);
    }

    @Override
    @Transactional
    public List<Pagamento> obterPorFalhou() {
        return pagamentoRepository.findByPagamentoFalhou()
                .stream()
                .map(pagamentoMapper::toModel)
                .collect(Collectors.toList());
    }
}
