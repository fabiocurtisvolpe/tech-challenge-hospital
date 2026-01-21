package com.adjt.agendamento.core.usecase.usuario;

import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.UsuarioValidator;

public class ObterPorIdUsuarioUseCase {

    private final UsuarioPort<Usuario> usuarioPort;

    private ObterPorIdUsuarioUseCase(UsuarioPort<Usuario> usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    public static ObterPorIdUsuarioUseCase create(UsuarioPort<Usuario> usuarioPort) {
        return new ObterPorIdUsuarioUseCase(usuarioPort);
    }

    public Usuario run(Integer id, String usuarioLogado) {

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);


        return null;
    }
}
