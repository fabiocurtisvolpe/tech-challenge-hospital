package com.adjt.agendamento.core.util;

import com.adjt.agendamento.core.exception.NotificacaoException;
import com.adjt.agendamento.core.model.Perfil;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioLogadoUtilTest {

    @Mock
    private UsuarioPort<Usuario> usuarioPort;

    @Test
    @DisplayName("Deve retornar usuário logado com sucesso")
    void deveRetornarUsuarioLogadoComSucesso() {
        String email = "usuario@email.com";
        Perfil perfil = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuarioEsperado = Usuario.builder()
                .id(1)
                .email(email)
                .nome("João Silva")
                .perfis(Set.of(perfil))
                .build();

        when(usuarioPort.obterPorEmail(email)).thenReturn(usuarioEsperado);

        Usuario resultado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, email);

        assertNotNull(resultado);
        assertEquals(usuarioEsperado.getId(), resultado.getId());
        assertEquals(usuarioEsperado.getEmail(), resultado.getEmail());
        assertEquals(usuarioEsperado.getNome(), resultado.getNome());
        verify(usuarioPort, times(1)).obterPorEmail(email);
    }

    @Test
    @DisplayName("Deve lançar exceção quando email for nulo")
    void deveLancarExcecaoQuandoEmailForNulo() {
        NotificacaoException exception = assertThrows(
                NotificacaoException.class,
                () -> UsuarioLogadoUtil.usuarioLogado(usuarioPort, null)
        );

        assertEquals(MensagemUtil.USUARIO_LOGADO_NAO_ENCONTRADO, exception.getMessage());
        verify(usuarioPort, never()).obterPorEmail(any());
    }

    @Test
    @DisplayName("Deve chamar obterPorEmail com email correto")
    void deveChamarObterPorEmailComEmailCorreto() {
        String email = "teste@email.com";
        Perfil perfil = Perfil.builder().nome("ROLE_ADMIN").build();
        Usuario usuario = Usuario.builder()
                .id(2)
                .email(email)
                .perfis(Set.of(perfil))
                .build();

        when(usuarioPort.obterPorEmail(email)).thenReturn(usuario);

        UsuarioLogadoUtil.usuarioLogado(usuarioPort, email);

        verify(usuarioPort).obterPorEmail(email);
    }
}
