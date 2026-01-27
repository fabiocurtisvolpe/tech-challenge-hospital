package com.adjt.notificacao.core.usecase;

import com.adjt.notificacao.core.model.Notificacao;
import com.adjt.notificacao.core.port.NotificacaoPort;
import com.adjt.notificacao.core.validator.NotificacaoValidator;

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
