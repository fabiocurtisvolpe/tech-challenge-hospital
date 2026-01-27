package com.adjt.notificacao.core.usecase;


import com.adjt.notificacao.core.model.Notificacao;
import com.adjt.notificacao.core.port.NotificacaoPort;

public class ExcluirNotificacaoUseCase {

    private final NotificacaoPort<Notificacao> notificacaoPort;

    private ExcluirNotificacaoUseCase(NotificacaoPort<Notificacao> notificacaoPort) {
        this.notificacaoPort = notificacaoPort;
    }

    public static ExcluirNotificacaoUseCase create(NotificacaoPort<Notificacao> notificacaoPort) {
        return new ExcluirNotificacaoUseCase(notificacaoPort);
    }

    public Boolean run(Integer id) {
        return notificacaoPort.excluir(id);
    }
}
