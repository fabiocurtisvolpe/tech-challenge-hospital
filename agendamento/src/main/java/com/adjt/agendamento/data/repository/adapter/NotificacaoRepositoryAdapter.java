package com.adjt.agendamento.data.repository.adapter;

import com.adjt.agendamento.core.model.Notificacao;
import com.adjt.agendamento.core.port.NotificacaoPort;
import com.adjt.agendamento.data.repository.jpa.NotificacaoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NotificacaoRepositoryAdapter implements NotificacaoPort<Notificacao> {

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoRepositoryAdapter(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    @Override
    public Optional<Notificacao> obterPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public Boolean excluir(Notificacao entidade) {
        return null;
    }

    @Override
    public List<Notificacao> obterTodas() {
        return List.of();
    }
}
