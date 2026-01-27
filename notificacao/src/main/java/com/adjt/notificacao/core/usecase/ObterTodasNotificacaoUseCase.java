package com.adjt.notificacao.core.usecase;

import com.adjt.notificacao.core.model.Notificacao;
import com.adjt.notificacao.core.port.NotificacaoPort;

import java.util.List;

public class ObterTodasNotificacaoUseCase {

    private final NotificacaoPort<Notificacao> notificacaoPort;

    private ObterTodasNotificacaoUseCase(NotificacaoPort<Notificacao> notificacaoPort) {
        this.notificacaoPort = notificacaoPort;
    }

    public static ObterTodasNotificacaoUseCase create(NotificacaoPort<Notificacao> notificacaoPort) {
        return new ObterTodasNotificacaoUseCase(notificacaoPort);
    }

    public List<Notificacao> run() {
        return notificacaoPort.obterTodas();
    }
}
