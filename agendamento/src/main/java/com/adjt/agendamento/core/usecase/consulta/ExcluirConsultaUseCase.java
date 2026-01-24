package com.adjt.agendamento.core.usecase.consulta;

import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.UsuarioValidator;

public class ExcluirConsultaUseCase {

    private final UsuarioPort<Usuario> usuarioPort;

    private ExcluirConsultaUseCase(UsuarioPort<Usuario> usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    public static ExcluirConsultaUseCase create(UsuarioPort<Usuario> usuarioPort) {
        return new ExcluirConsultaUseCase(usuarioPort);
    }

    public Boolean run(Integer id, String usuarioLogado) {

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);
        Usuario excluirUsuario = usuarioPort.obterPorId(id);
        UsuarioValidator.validarPermissaoExcluir(excluirUsuario, usrLogado);

        return usuarioPort.excluir(id);
    }
}
