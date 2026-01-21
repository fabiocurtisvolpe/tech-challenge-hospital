package com.adjt.agendamento.core.usecase.usuario;

import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.UsuarioValidator;

public class ExcluirUsuarioUseCase {

    private final UsuarioPort<Usuario> usuarioPort;

    private ExcluirUsuarioUseCase(UsuarioPort<Usuario> usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    public static ExcluirUsuarioUseCase create(UsuarioPort<Usuario> usuarioPort) {
        return new ExcluirUsuarioUseCase(usuarioPort);
    }

    public Boolean run(Usuario usuario, String usuarioLogado) {

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);
        UsuarioValidator.validarPermissaoExcluir(usuario, usrLogado);

        return usuarioPort.excluir(usuario.getId());
    }
}
