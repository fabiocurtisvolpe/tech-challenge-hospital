package com.adjt.agendamento.core.util;

import com.adjt.agendamento.core.exception.NotificacaoException;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;

import java.util.Objects;

public class UsuarioLogadoUtil {

    public static Usuario usuarioLogado(UsuarioPort<Usuario> usuarioPort, String email) {

        if (Objects.isNull(email)) {
            throw new NotificacaoException(MensagemUtil.USUARIO_LOGADO_NAO_ENCONTRADO);
        }

        return usuarioPort.obterPorEmail(email);
    }
}
