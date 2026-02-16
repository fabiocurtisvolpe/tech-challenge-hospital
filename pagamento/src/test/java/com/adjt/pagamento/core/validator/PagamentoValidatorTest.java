package com.adjt.pagamento.core.validator;

import com.adjt.pagamento.core.enums.StatusPagamentoEnum;
import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.util.MensagemUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoValidatorTest {

    @Test
    @DisplayName("Deve validar cadastro/atualização com sucesso quando todos os campos obrigatórios estão preenchidos")
    void deveValidarCadastroAtualizacaoComSucesso() {
        Pagamento pagamento = Pagamento.builder()
                .idConsulta(1)
                .idPaciente(1)
                .valor(BigDecimal.valueOf(150.00))
                .build();

        assertDoesNotThrow(() -> PagamentoValidator.cadastarAtualizar(pagamento));
    }

    @Test
    @DisplayName("Deve validar cadastro/atualização com sucesso quando todos os campos estão preenchidos")
    void deveValidarCadastroAtualizacaoComTodosCamposPreenchidos() {
        Pagamento pagamento = Pagamento.builder()
                .id(1)
                .dataCriacao(LocalDateTime.now())
                .dataHora(LocalDateTime.now())
                .idConsulta(1)
                .idPaciente(1)
                .valor(BigDecimal.valueOf(150.00))
                .responseCode(200)
                .status(StatusPagamentoEnum.APROVADO_PAGAMENTO)
                .build();

        assertDoesNotThrow(() -> PagamentoValidator.cadastarAtualizar(pagamento));
    }

    @Test
    @DisplayName("Deve lançar exceção quando idConsulta for nulo")
    void deveLancarExcecaoQuandoIdConsultaForNulo() {
        Pagamento pagamento = Pagamento.builder()
                .idPaciente(1)
                .valor(BigDecimal.valueOf(150.00))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PagamentoValidator.cadastarAtualizar(pagamento)
        );

        assertEquals(MensagemUtil.ID_CONSULTA_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando idPaciente for nulo")
    void deveLancarExcecaoQuandoIdPacienteForNulo() {
        Pagamento pagamento = Pagamento.builder()
                .idConsulta(1)
                .valor(BigDecimal.valueOf(150.00))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PagamentoValidator.cadastarAtualizar(pagamento)
        );

        assertEquals(MensagemUtil.ID_PACIENTE_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando valor for nulo")
    void deveLancarExcecaoQuandoValorForNulo() {
        Pagamento pagamento = Pagamento.builder()
                .idConsulta(1)
                .idPaciente(1)
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PagamentoValidator.cadastarAtualizar(pagamento)
        );

        assertEquals(MensagemUtil.VALOR_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando todos os campos obrigatórios forem nulos")
    void deveLancarExcecaoQuandoTodosCamposObrigatoriosForemNulos() {
        Pagamento pagamento = Pagamento.builder().build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> PagamentoValidator.cadastarAtualizar(pagamento)
        );

        // Valida que pelo menos uma das mensagens de erro é lançada
        assertTrue(
                exception.getMessage().equals(MensagemUtil.ID_CONSULTA_VAZIO) ||
                exception.getMessage().equals(MensagemUtil.ID_PACIENTE_VAZIO) ||
                exception.getMessage().equals(MensagemUtil.VALOR_VAZIO)
        );
    }

    @Test
    @DisplayName("Deve validar quando valor for zero")
    void deveValidarQuandoValorForZero() {
        Pagamento pagamento = Pagamento.builder()
                .idConsulta(1)
                .idPaciente(1)
                .valor(BigDecimal.ZERO)
                .build();

        assertDoesNotThrow(() -> PagamentoValidator.cadastarAtualizar(pagamento));
    }

    @Test
    @DisplayName("Deve validar quando valor for negativo")
    void deveValidarQuandoValorForNegativo() {
        Pagamento pagamento = Pagamento.builder()
                .idConsulta(1)
                .idPaciente(1)
                .valor(BigDecimal.valueOf(-50.00))
                .build();

        assertDoesNotThrow(() -> PagamentoValidator.cadastarAtualizar(pagamento));
    }

    @Test
    @DisplayName("Deve validar com valores de ID válidos")
    void deveValidarComValoresIdValidos() {
        Pagamento pagamento = Pagamento.builder()
                .idConsulta(999)
                .idPaciente(888)
                .valor(BigDecimal.valueOf(250.50))
                .build();

        assertDoesNotThrow(() -> PagamentoValidator.cadastarAtualizar(pagamento));
    }

    @Test
    @DisplayName("Deve validar com valor decimal preciso")
    void deveValidarComValorDecimalPreciso() {
        Pagamento pagamento = Pagamento.builder()
                .idConsulta(1)
                .idPaciente(1)
                .valor(new BigDecimal("123.45"))
                .build();

        assertDoesNotThrow(() -> PagamentoValidator.cadastarAtualizar(pagamento));
    }
}
