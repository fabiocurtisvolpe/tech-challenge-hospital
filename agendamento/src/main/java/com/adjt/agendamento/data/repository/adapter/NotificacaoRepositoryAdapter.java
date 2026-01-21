package com.adjt.agendamento.data.repository.adapter;

import com.adjt.agendamento.core.model.Notificacao;
import com.adjt.agendamento.core.port.NotificacaoPort;
import com.adjt.agendamento.core.util.MensagemUtil;
import com.adjt.agendamento.data.entity.NotificacaoEntity;
import com.adjt.agendamento.data.mapper.NotificacaoMapper;
import com.adjt.agendamento.data.repository.jpa.NotificacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class NotificacaoRepositoryAdapter implements NotificacaoPort<Notificacao> {

    private final NotificacaoRepository notificacaoRepository;
    private final NotificacaoMapper notificacaoMapper;

    public NotificacaoRepositoryAdapter(NotificacaoRepository notificacaoRepository,
                                        NotificacaoMapper notificacaoMapper) {
        this.notificacaoRepository = notificacaoRepository;
        this.notificacaoMapper = notificacaoMapper;
    }

    @Override
    @Transactional
    public Notificacao criar(Notificacao model) {
        NotificacaoEntity entity = notificacaoMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        NotificacaoEntity savedEntity = notificacaoRepository.save(entity);
        return notificacaoMapper.toModel(savedEntity);
    }

    @Override
    @Transactional
    public Notificacao atualizar(Notificacao model) {
        NotificacaoEntity entity = notificacaoMapper.toEntity(model);
        Objects.requireNonNull(entity, MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);

        NotificacaoEntity savedEntity = notificacaoRepository.save(entity);
        return notificacaoMapper.toModel(savedEntity);
    }

    @Override
    public Boolean excluir(Integer id) {
        NotificacaoEntity entity = notificacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.NOTIFICACAO_NAO_ENCONTRADA));

        notificacaoRepository.delete(entity);
        return true;
    }

    @Transactional
    public Notificacao obterPorId(Integer id) {
        NotificacaoEntity entity = notificacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemUtil.NOTIFICACAO_NAO_ENCONTRADA));

        return notificacaoMapper.toModel(entity);
    }

    @Override
    public List<Notificacao> obterTodas() {
        List<NotificacaoEntity> notificacaoEntityList = notificacaoRepository.findAll();
        return notificacaoMapper.toModelList(notificacaoEntityList);
    }
}
