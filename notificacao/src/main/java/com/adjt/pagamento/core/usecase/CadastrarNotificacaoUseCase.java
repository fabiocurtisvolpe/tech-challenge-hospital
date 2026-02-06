package com.adjt.pagamento.core.usecase;

import com.adjt.pagamento.core.model.Notificacao;
import com.adjt.pagamento.core.port.NotificacaoPort;
import com.adjt.pagamento.core.validator.NotificacaoValidator;

public class CadastrarNotificacaoUseCase {

    private final NotificacaoPort<Notificacao> notificacaoPort;

    private CadastrarNotificacaoUseCase(NotificacaoPort<Notificacao> notificacaoPort) {
        this.notificacaoPort = notificacaoPort;
    }

    public static CadastrarNotificacaoUseCase create(NotificacaoPort<Notificacao> notificacaoPort) {
        return new CadastrarNotificacaoUseCase(notificacaoPort);
    }

    public Notificacao run(Notificacao notificacao) {

        NotificacaoValidator.validarCamposObrigatorios(notificacao);
        return notificacaoPort.criar(notificacao);
    }
}
