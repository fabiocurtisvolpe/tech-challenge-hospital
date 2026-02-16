package com.adjt.agendamento.core.validator;

import com.adjt.agendamento.core.exception.NotificacaoException;
import com.adjt.agendamento.core.model.Perfil;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.util.MensagemUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioValidatorTest {

    @Test
    @DisplayName("Deve validar id com sucesso")
    void deveValidarIdComSucesso() {
        Usuario usuario = Usuario.builder()
                .id(1)
                .build();

        assertDoesNotThrow(() -> UsuarioValidator.validarId(usuario));
    }

    @Test
    @DisplayName("Deve lançar exceção quando id for nulo")
    void deveLancarExcecaoQuandoIdForNulo() {
        Usuario usuario = Usuario.builder().build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarId(usuario)
        );

        assertEquals(MensagemUtil.ID_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve validar campos obrigatórios com sucesso")
    void deveValidarCamposObrigatoriosComSucesso() {
        Perfil perfil = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuario = Usuario.builder()
                .nome("João Silva")
                .email("joao@email.com")
                .senha("senha123")
                .perfis(Set.of(perfil))
                .telefone("11987654321")
                .build();

        assertDoesNotThrow(() -> UsuarioValidator.validarCamposObrigatorios(usuario));
    }

    @Test
    @DisplayName("Deve validar campos obrigatórios sem telefone")
    void deveValidarCamposObrigatoriosSemTelefone() {
        Perfil perfil = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuario = Usuario.builder()
                .nome("João Silva")
                .email("joao@email.com")
                .senha("senha123")
                .perfis(Set.of(perfil))
                .build();

        assertDoesNotThrow(() -> UsuarioValidator.validarCamposObrigatorios(usuario));
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome for nulo")
    void deveLancarExcecaoQuandoNomeForNulo() {
        Perfil perfil = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuario = Usuario.builder()
                .email("joao@email.com")
                .senha("senha123")
                .perfis(Set.of(perfil))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarCamposObrigatorios(usuario)
        );

        assertEquals(MensagemUtil.USUARIO_NOME_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome for vazio")
    void deveLancarExcecaoQuandoNomeForVazio() {
        Perfil perfil = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuario = Usuario.builder()
                .nome("   ")
                .email("joao@email.com")
                .senha("senha123")
                .perfis(Set.of(perfil))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarCamposObrigatorios(usuario)
        );

        assertEquals(MensagemUtil.USUARIO_NOME_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email for nulo")
    void deveLancarExcecaoQuandoEmailForNulo() {
        Perfil perfil = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuario = Usuario.builder()
                .nome("João Silva")
                .senha("senha123")
                .perfis(Set.of(perfil))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarCamposObrigatorios(usuario)
        );

        assertEquals(MensagemUtil.USUARIO_EMAIL_INVALIDO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email for inválido")
    void deveLancarExcecaoQuandoEmailForInvalido() {
        Perfil perfil = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuario = Usuario.builder()
                .nome("João Silva")
                .email("email_invalido")
                .senha("senha123")
                .perfis(Set.of(perfil))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarCamposObrigatorios(usuario)
        );

        assertEquals(MensagemUtil.USUARIO_EMAIL_INVALIDO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando senha for nula")
    void deveLancarExcecaoQuandoSenhaForNula() {
        Perfil perfil = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuario = Usuario.builder()
                .nome("João Silva")
                .email("joao@email.com")
                .perfis(Set.of(perfil))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarCamposObrigatorios(usuario)
        );

        assertEquals(MensagemUtil.USUARIO_SENHA_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando senha for vazia")
    void deveLancarExcecaoQuandoSenhaForVazia() {
        Perfil perfil = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuario = Usuario.builder()
                .nome("João Silva")
                .email("joao@email.com")
                .senha("   ")
                .perfis(Set.of(perfil))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarCamposObrigatorios(usuario)
        );

        assertEquals(MensagemUtil.USUARIO_SENHA_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando perfis for nulo")
    void deveLancarExcecaoQuandoPerfisForNulo() {
        Usuario usuario = Usuario.builder()
                .nome("João Silva")
                .email("joao@email.com")
                .senha("senha123")
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarCamposObrigatorios(usuario)
        );

        assertEquals(MensagemUtil.USUARIO_PERFIL_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando perfis for vazio")
    void deveLancarExcecaoQuandoPerfisForVazio() {
        Usuario usuario = Usuario.builder()
                .nome("João Silva")
                .email("joao@email.com")
                .senha("senha123")
                .perfis(Set.of())
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarCamposObrigatorios(usuario)
        );

        assertEquals(MensagemUtil.USUARIO_PERFIL_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando perfil for inválido")
    void deveLancarExcecaoQuandoPerfilForInvalido() {
        Perfil perfil = Perfil.builder().nome("ROLE_INVALIDA").build();
        Usuario usuario = Usuario.builder()
                .nome("João Silva")
                .email("joao@email.com")
                .senha("senha123")
                .perfis(Set.of(perfil))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarCamposObrigatorios(usuario)
        );

        assertEquals(MensagemUtil.USUARIO_PERFIL_INVALIDO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando telefone for inválido")
    void deveLancarExcecaoQuandoTelefoneForInvalido() {
        Perfil perfil = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuario = Usuario.builder()
                .nome("João Silva")
                .email("joao@email.com")
                .senha("senha123")
                .perfis(Set.of(perfil))
                .telefone("123")
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarCamposObrigatorios(usuario)
        );

        assertEquals(MensagemUtil.USUARIO_TELEFONE_INVALIDO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve validar permissão quando usuário logado é admin e alvo não é paciente")
    void deveValidarPermissaoQuandoAdminEAlvoNaoEhPaciente() {
        Perfil perfilAdmin = Perfil.builder().nome("ROLE_ADMIN").build();
        Perfil perfilMedico = Perfil.builder().nome("ROLE_MEDICO").build();
        
        Usuario usrLogado = Usuario.builder().perfis(Set.of(perfilAdmin)).build();
        Usuario alvo = Usuario.builder().perfis(Set.of(perfilMedico)).build();

        assertDoesNotThrow(() -> UsuarioValidator.validarPermissao(alvo, usrLogado));
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário logado é paciente")
    void deveLancarExcecaoQuandoUsuarioLogadoEhPaciente() {
        Perfil perfilPaciente = Perfil.builder().nome("ROLE_PACIENTE").build();
        Usuario usrLogado = Usuario.builder().perfis(Set.of(perfilPaciente)).build();
        Usuario alvo = Usuario.builder().perfis(Set.of(perfilPaciente)).build();

        NotificacaoException exception = assertThrows(
                NotificacaoException.class,
                () -> UsuarioValidator.validarPermissao(alvo, usrLogado)
        );

        assertEquals(MensagemUtil.PERMISSAO_NEGADA, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando médico tenta alterar não-paciente")
    void deveLancarExcecaoQuandoMedicoTentaAlterarNaoPaciente() {
        Perfil perfilMedico = Perfil.builder().nome("ROLE_MEDICO").build();
        Perfil perfilEnfermeiro = Perfil.builder().nome("ROLE_ENFERMEIRO").build();
        
        Usuario usrLogado = Usuario.builder().perfis(Set.of(perfilMedico)).build();
        Usuario alvo = Usuario.builder().perfis(Set.of(perfilEnfermeiro)).build();

        NotificacaoException exception = assertThrows(
                NotificacaoException.class,
                () -> UsuarioValidator.validarPermissao(alvo, usrLogado)
        );

        assertEquals(MensagemUtil.PERMISSAO_NEGADA, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando admin tenta alterar paciente")
    void deveLancarExcecaoQuandoAdminTentaAlterarPaciente() {
        Perfil perfilAdmin = Perfil.builder().nome("ROLE_ADMIN").build();
        Perfil perfilPaciente = Perfil.builder().nome("ROLE_PACIENTE").build();
        
        Usuario usrLogado = Usuario.builder().perfis(Set.of(perfilAdmin)).build();
        Usuario alvo = Usuario.builder().perfis(Set.of(perfilPaciente)).build();

        NotificacaoException exception = assertThrows(
                NotificacaoException.class,
                () -> UsuarioValidator.validarPermissao(alvo, usrLogado)
        );

        assertEquals(MensagemUtil.PERMISSAO_NEGADA, exception.getMessage());
    }

    @Test
    @DisplayName("Deve validar permissão para excluir")
    void deveValidarPermissaoParaExcluir() {
        Perfil perfilAdmin = Perfil.builder().nome("ROLE_ADMIN").build();
        Perfil perfilMedico = Perfil.builder().nome("ROLE_MEDICO").build();
        
        Usuario usrLogado = Usuario.builder()
                .email("admin@email.com")
                .perfis(Set.of(perfilAdmin))
                .build();
        
        Usuario alvo = Usuario.builder()
                .id(1)
                .email("medico@email.com")
                .perfis(Set.of(perfilMedico))
                .build();

        assertDoesNotThrow(() -> UsuarioValidator.validarPermissaoExcluir(alvo, usrLogado));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar excluir a si mesmo")
    void deveLancarExcecaoAoTentarExcluirASiMesmo() {
        Perfil perfilAdmin = Perfil.builder().nome("ROLE_ADMIN").build();
        
        Usuario usrLogado = Usuario.builder()
                .id(1)
                .email("admin@email.com")
                .perfis(Set.of(perfilAdmin))
                .build();
        
        Usuario alvo = Usuario.builder()
                .id(1)
                .email("admin@email.com")
                .perfis(Set.of(perfilAdmin))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarPermissaoExcluir(alvo, usrLogado)
        );

        assertEquals(MensagemUtil.PERMISSAO_NEGADA, exception.getMessage());
    }

    @Test
    @DisplayName("Deve validar permissão para buscar")
    void deveValidarPermissaoParaBuscar() {
        Perfil perfil = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuario = Usuario.builder()
                .perfis(Set.of(perfil))
                .build();

        assertDoesNotThrow(() -> UsuarioValidator.validarPermissaoBuscar(usuario));
    }

    @Test
    @DisplayName("Deve lançar exceção quando perfil inválido ao buscar")
    void deveLancarExcecaoQuandoPerfilInvalidoAoBuscar() {
        Perfil perfil = Perfil.builder().nome("ROLE_INVALIDA").build();
        Usuario usuario = Usuario.builder()
                .perfis(Set.of(perfil))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> UsuarioValidator.validarPermissaoBuscar(usuario)
        );

        assertEquals(MensagemUtil.USUARIO_PERFIL_INVALIDO, exception.getMessage());
    }
}
