package com.adjt.agendamento.core.validator;

import com.adjt.agendamento.core.exception.NotificacaoException;
import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.util.MensagemUtil;

public class ConsultaValidator {

    public static void validarId(Consulta consulta) {
        if (consulta.getId() == null) {
            throw new IllegalArgumentException(MensagemUtil.ID_VAZIO);
        }
    }
    
    public static void validarPermissao(Usuario usrLogado) {

        if (usrLogado.isSomentePaciente()) {
            throw new NotificacaoException(MensagemUtil.PERMISSAO_NEGADA);
        }
    }

    public static void validarCamposObrigatorios(Consulta consulta) {

        if (consulta.getDataHora() == null) {
            throw new IllegalArgumentException(MensagemUtil.CONSULTA_DATA_HORA_VAZIO);
        }

        if (consulta.getPaciente() == null) {
            throw new IllegalArgumentException(MensagemUtil.CONSULTA_PACIENTE_VAZIO);
        }

        if (consulta.getEspecialidade() == null) {
            throw new IllegalArgumentException(MensagemUtil.CONSULTA_ESPECIALIDADE_VAZIO);
        }

        if (consulta.getMedico() == null) {
            throw new IllegalArgumentException(MensagemUtil.CONSULTA_MEDICO_VAZIO);
        }

    }
}
