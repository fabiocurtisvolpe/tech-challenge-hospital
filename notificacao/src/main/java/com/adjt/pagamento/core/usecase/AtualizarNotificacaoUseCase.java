package com.adjt.pagamento.core.usecase;

import com.adjt.pagamento.core.model.Notificacao;
import com.adjt.pagamento.core.port.NotificacaoPort;
import com.adjt.pagamento.core.validator.NotificacaoValidator;

public class AtualizarNotificacaoUseCase {

    private final NotificacaoPort<Notificacao> notificacaoPort;

    private AtualizarNotificacaoUseCase(NotificacaoPort<Notificacao> notificacaoPort) {
        this.notificacaoPort = notificacaoPort;
    }

    public static AtualizarNotificacaoUseCase create(NotificacaoPort<Notificacao> notificacaoPort) {
        return new AtualizarNotificacaoUseCase(notificacaoPort);
    }

    public Notificacao run(Notificacao notificacao) {

        NotificacaoValidator.validarId(notificacao);
        NotificacaoValidator.validarCamposObrigatorios(notificacao);

        return notificacaoPort.atualizar(notificacao);
    }
}
