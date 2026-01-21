package com.adjt.agendamento.core.usecase.usuario;

import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.UsuarioValidator;

public class AtualizarUsuarioUseCase {

    private final UsuarioPort<Usuario> usuarioPort;

    private AtualizarUsuarioUseCase(UsuarioPort<Usuario> usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    public static AtualizarUsuarioUseCase create(UsuarioPort<Usuario> usuarioPort) {
        return new AtualizarUsuarioUseCase(usuarioPort);
    }

    public Usuario run(Usuario usuario, String usuarioLogado) {

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);
        UsuarioValidator.validarPermissao(usuario, usrLogado);
        UsuarioValidator.validarCamposObrigatorios(usuario);

        return usuarioPort.atualizar(usuario);
    }
}
