package com.adjt.agendamento.core.usecase.usuario;

import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.UsuarioValidator;

public class ObterPorEmailUsuarioUseCase {

    private final UsuarioPort<Usuario> usuarioPort;

    private ObterPorEmailUsuarioUseCase(UsuarioPort<Usuario> usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    public static ObterPorEmailUsuarioUseCase create(UsuarioPort<Usuario> usuarioPort) {
        return new ObterPorEmailUsuarioUseCase(usuarioPort);
    }

    public Usuario run(String email) {

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, email);
        UsuarioValidator.validarPermissaoBuscar(usrLogado);

        return usuarioPort.obterPorEmail(email);
    }
}
