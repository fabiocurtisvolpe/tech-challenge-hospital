package com.adjt.agendamento.core.usecase.usuario;

import com.adjt.agendamento.core.exception.NotificacaoException;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.MensagemUtil;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CadastrarUsuarioUseCase {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final List<String> PERFIS_PERMITIDOS = Arrays.asList("ROLE_MEDICO", "ROLE_ENFERMEIRO", "ROLE_PACIENTE", "ROLE_ADMIN");

    private final UsuarioPort<Usuario> usuarioPort;

    private CadastrarUsuarioUseCase(UsuarioPort<Usuario> usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    public static CadastrarUsuarioUseCase create(UsuarioPort<Usuario> usuarioPort) {
        return new CadastrarUsuarioUseCase(usuarioPort);
    }

    public Usuario run(Usuario usuario, String usuarioLogado) {

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);
        validarPermissao(usrLogado);
        validarCadastro(usuario);

        return usuarioPort.criar(usuario);
    }

    private void validarPermissao(Usuario usuario) {

        if (!usuario.isAdmin()) {
            throw new NotificacaoException(MensagemUtil.PERMISSAO_NEGADA);
        }
    }

    private void validarCadastro(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException(MensagemUtil.USUARIO_NOME_VAZIO);
        }

        if (usuario.getEmail() == null || !Pattern.compile(EMAIL_REGEX).matcher(usuario.getEmail()).matches()) {
            throw new IllegalArgumentException(MensagemUtil.USUARIO_EMAIL_INVALIDO);
        }

        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException(MensagemUtil.USUARIO_SENHA_VAZIO);
        }

        if (usuario.getPerfis() == null || usuario.getPerfis().isEmpty()) {
            throw new IllegalArgumentException(MensagemUtil.USUARIO_PERFIL_VAZIO);
        }

        boolean temPerfilValido = usuario.getPerfis().stream()
                .anyMatch(p -> p.getNome() != null && PERFIS_PERMITIDOS.contains(p.getNome().toUpperCase()));

        if (!temPerfilValido) {
            throw new IllegalArgumentException(MensagemUtil.USUARIO_PERFIL_INVALIDO);
        }
    }
}
