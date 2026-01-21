package com.adjt.agendamento.core.validator;

import com.adjt.agendamento.core.exception.NotificacaoException;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.util.MensagemUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class UsuarioValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final List<String> PERFIS_PERMITIDOS = Arrays.asList("ROLE_MEDICO", "ROLE_ENFERMEIRO", "ROLE_PACIENTE", "ROLE_ADMIN");

    public static void validarCamposObrigatorios(Usuario usuario) {

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

        if (!usuario.hasAnyRole(PERFIS_PERMITIDOS)) {
            throw new IllegalArgumentException(MensagemUtil.USUARIO_PERFIL_INVALIDO);
        }
    }

    private static void validarPermissaoComum(Usuario usuario, Usuario usrLogado) {
        // 1) Se o utilizador logado for paciente, não pode ninguém
        if (usrLogado.isPaciente()) {
            throw new NotificacaoException(MensagemUtil.PERMISSAO_NEGADA);
        }

        // 2) Se o usuário logado for médico ou enfermeiro, só pode se o alvo for paciente
        if ((usrLogado.isMedico() || usrLogado.isEnfermeiro()) && !usuario.isPaciente()) {
            throw new NotificacaoException(MensagemUtil.PERMISSAO_NEGADA);
        }

        // 3) Se o utilizador logado for admin, só pode excluir médico, enfermeiro ou outro admin (não pode paciente)
        if (usrLogado.isAdmin() && usuario.isPaciente()) {
            throw new NotificacaoException(MensagemUtil.PERMISSAO_NEGADA);
        }
    }

    public static void validarPermissao(Usuario usuario, Usuario usrLogado) {
        validarPermissaoComum(usuario, usrLogado);

        // 4. Segurança: Não permitir ação sobre o próprio e-mail logado (evitar auto alteração de perfil crítico)
        if (Objects.equals(usuario.getEmail(), usrLogado.getEmail())) {
            throw new IllegalArgumentException(MensagemUtil.USUARIO_EMAIL_NAO_PERMITIDO);
        }
    }

    public static void validarPermissaoExcluir(Usuario usuario, Usuario usrLogado) {
        validarPermissaoComum(usuario, usrLogado);

        // Regra de Segurança Adicional: Um utilizador não deve poder excluir a si
        if (Objects.equals(usuario.getEmail(), usrLogado.getEmail())) {
            throw new IllegalArgumentException(MensagemUtil.PERMISSAO_NEGADA);
        }
    }

    public static void validarPermissaoBuscar(Usuario usuario) {

        if (!usuario.hasAnyRole(PERFIS_PERMITIDOS)) {
            throw new IllegalArgumentException(MensagemUtil.USUARIO_PERFIL_INVALIDO);
        }


    }
}
