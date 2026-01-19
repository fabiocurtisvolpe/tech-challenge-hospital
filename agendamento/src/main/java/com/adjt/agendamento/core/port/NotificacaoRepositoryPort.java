package com.adjt.agendamento.core.port;

import com.adjt.agendamento.core.model.Notificacao;

import java.util.List;
import java.util.Optional;

public interface NotificacaoRepositoryPort {

    Optional<Notificacao> obterPorId(Integer id);
    Boolean excluir(Notificacao entidade);
    List<Notificacao> obterTodas();
}
