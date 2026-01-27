package com.adjt.notificacao.core.validator;

import com.adjt.notificacao.core.model.Notificacao;
import com.adjt.notificacao.core.util.MensagemUtil;

public class NotificacaoValidator {

    public static void validarId(Notificacao notificacao) {
        if (notificacao.getId() == null) {
            throw new IllegalArgumentException(MensagemUtil.ID_VAZIO);
        }
    }

    public static void validarCamposObrigatorios(Notificacao notificacao) {

        if (notificacao.getDataHora() == null) {
            throw new IllegalArgumentException(MensagemUtil.DATA_HORA_VAZIO);
        }

        if (notificacao.getNomePaciente() == null) {
            throw new IllegalArgumentException(MensagemUtil.PACIENTE_VAZIO);
        }

        if (notificacao.getTelefonePaciente() == null) {
            throw new IllegalArgumentException(MensagemUtil.PACIENTE_TELEFONE_INVALIDO);
        }

        if (notificacao.getEspecialidade() == null) {
            throw new IllegalArgumentException(MensagemUtil.ESPECIALIDADE_VAZIO);
        }

        if (notificacao.getEmailPaciente() == null) {
            throw new IllegalArgumentException(MensagemUtil.PACIENTE_EMAIL_INVALIDO);
        }

        if (notificacao.getNomeMedico() == null) {
            throw new IllegalArgumentException(MensagemUtil.MEDICO_VAZIO);
        }
    }
}
