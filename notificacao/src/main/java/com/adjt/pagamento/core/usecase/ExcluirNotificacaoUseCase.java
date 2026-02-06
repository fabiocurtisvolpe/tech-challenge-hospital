package com.adjt.pagamento.core.usecase;


import com.adjt.pagamento.core.model.Notificacao;
import com.adjt.pagamento.core.port.NotificacaoPort;

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
