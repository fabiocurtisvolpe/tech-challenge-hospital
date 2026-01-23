package com.adjt.agendamento.core.usecase.usuario;

import com.adjt.agendamento.core.model.Perfil;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.PerfilPort;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.UsuarioValidator;

import java.util.Set;

public class AtualizarUsuarioUseCase {

    private final UsuarioPort<Usuario> usuarioPort;
    private final PerfilPort<Perfil> perfilPort;

    private AtualizarUsuarioUseCase(UsuarioPort<Usuario> usuarioPort,
                                    PerfilPort<Perfil> perfilPort) {
        this.usuarioPort = usuarioPort;
        this.perfilPort = perfilPort;
    }

    public static AtualizarUsuarioUseCase create(UsuarioPort<Usuario> usuarioPort,
                                                 PerfilPort<Perfil> perfilPort) {
        return new AtualizarUsuarioUseCase(usuarioPort, perfilPort);
    }

    public Usuario run(Usuario usuario, Set<Integer> perfisIds, String usuarioLogado) {

        Set<Perfil> perfis = this.perfilPort.buscarPorIds(perfisIds);

        Usuario alterarUsuario = Usuario.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .perfis(perfis)
                .ativo(true)
                .build();


        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);
        UsuarioValidator.validarId(alterarUsuario);
        UsuarioValidator.validarPermissao(alterarUsuario, usrLogado);
        UsuarioValidator.validarCamposObrigatorios(alterarUsuario);

        return usuarioPort.atualizar(alterarUsuario);
    }
}
