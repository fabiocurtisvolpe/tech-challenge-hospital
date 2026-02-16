package com.adjt.agendamento.core.validator;

import com.adjt.agendamento.core.exception.NotificacaoException;
import com.adjt.agendamento.core.util.MensagemUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginadoValidatorTest {

    @Test
    @DisplayName("Deve validar paginação com sucesso")
    void deveValidarPaginacaoComSucesso() {
        assertDoesNotThrow(() -> PaginadoValidator.validarPagina(0, 10));
        assertDoesNotThrow(() -> PaginadoValidator.validarPagina(1, 20));
        assertDoesNotThrow(() -> PaginadoValidator.validarPagina(5, 50));
    }

    @Test
    @DisplayName("Deve lançar exceção quando page for negativo")
    void deveLancarExcecaoQuandoPageForNegativo() {
        NotificacaoException exception = assertThrows(
                NotificacaoException.class,
                () -> PaginadoValidator.validarPagina(-1, 10)
        );

        assertEquals(MensagemUtil.PAGINA_SIZE_INVALIDA, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando size for zero")
    void deveLancarExcecaoQuandoSizeForZero() {
        NotificacaoException exception = assertThrows(
                NotificacaoException.class,
                () -> PaginadoValidator.validarPagina(0, 0)
        );

        assertEquals(MensagemUtil.PAGINA_SIZE_INVALIDA, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando size for negativo")
    void deveLancarExcecaoQuandoSizeForNegativo() {
        NotificacaoException exception = assertThrows(
                NotificacaoException.class,
                () -> PaginadoValidator.validarPagina(0, -5)
        );

        assertEquals(MensagemUtil.PAGINA_SIZE_INVALIDA, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando page e size forem inválidos")
    void deveLancarExcecaoQuandoPageESizeForemInvalidos() {
        NotificacaoException exception = assertThrows(
                NotificacaoException.class,
                () -> PaginadoValidator.validarPagina(-1, -1)
        );

        assertEquals(MensagemUtil.PAGINA_SIZE_INVALIDA, exception.getMessage());
    }
}
