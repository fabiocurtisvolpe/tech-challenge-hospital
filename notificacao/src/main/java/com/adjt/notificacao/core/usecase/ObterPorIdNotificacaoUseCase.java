package com.adjt.notificacao.core.usecase;

import com.adjt.notificacao.core.model.Notificacao;
import com.adjt.notificacao.core.port.NotificacaoPort;

public class ObterPorIdNotificacaoUseCase {

    private final NotificacaoPort<Notificacao> notificacaoPort;

    private ObterPorIdNotificacaoUseCase(NotificacaoPort<Notificacao> notificacaoPort) {
        this.notificacaoPort = notificacaoPort;
    }

    public static ObterPorIdNotificacaoUseCase create(NotificacaoPort<Notificacao> notificacaoPort) {
        return new ObterPorIdNotificacaoUseCase(notificacaoPort);
    }

    public Notificacao run(Integer id) {
        return notificacaoPort.obterPorId(id);
    }
}
