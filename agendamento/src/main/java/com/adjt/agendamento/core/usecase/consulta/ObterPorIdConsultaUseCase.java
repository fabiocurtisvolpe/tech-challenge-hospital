package com.adjt.agendamento.core.usecase.consulta;

import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.UsuarioValidator;

public class ObterPorIdConsultaUseCase {

    private final UsuarioPort<Usuario> usuarioPort;

    private ObterPorIdConsultaUseCase(UsuarioPort<Usuario> usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    public static ObterPorIdConsultaUseCase create(UsuarioPort<Usuario> usuarioPort) {
        return new ObterPorIdConsultaUseCase(usuarioPort);
    }

    public Usuario run(Integer id, String usuarioLogado) {

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);
        UsuarioValidator.validarPermissaoBuscar(usrLogado);

        return usuarioPort.obterPorId(id);
    }
}
