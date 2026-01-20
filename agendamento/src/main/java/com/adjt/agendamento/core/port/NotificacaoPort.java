package com.adjt.agendamento.core.port;

import java.util.List;
import java.util.Optional;

public interface NotificacaoPort<Notificacao> {

    Optional<Notificacao> obterPorId(Integer id);
    Boolean excluir(Notificacao entidade);
    List<Notificacao> obterTodas();
}
