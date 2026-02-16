package com.adjt.agendamento.core.validator;

import com.adjt.agendamento.core.exception.NotificacaoException;
import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.model.Especialidade;
import com.adjt.agendamento.core.model.Perfil;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.util.MensagemUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ConsultaValidatorTest {

    @Test
    @DisplayName("Deve validar id com sucesso")
    void deveValidarIdComSucesso() {
        Consulta consulta = Consulta.builder()
                .id(1)
                .build();

        assertDoesNotThrow(() -> ConsultaValidator.validarId(consulta));
    }

    @Test
    @DisplayName("Deve lançar exceção quando id for nulo")
    void deveLancarExcecaoQuandoIdForNulo() {
        Consulta consulta = Consulta.builder().build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ConsultaValidator.validarId(consulta)
        );

        assertEquals(MensagemUtil.ID_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve validar permissão quando usuário não é somente paciente")
    void deveValidarPermissaoQuandoUsuarioNaoEhSomentePaciente() {
        Perfil perfilMedico = Perfil.builder().nome("ROLE_MEDICO").build();
        Usuario usuario = Usuario.builder()
                .perfis(Set.of(perfilMedico))
                .build();

        assertDoesNotThrow(() -> ConsultaValidator.validarPermissao(usuario));
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário é somente paciente")
    void deveLancarExcecaoQuandoUsuarioEhSomentePaciente() {
        Perfil perfilPaciente = Perfil.builder().nome("ROLE_PACIENTE").build();
        Usuario usuario = Usuario.builder()
                .perfis(Set.of(perfilPaciente))
                .build();

        NotificacaoException exception = assertThrows(
                NotificacaoException.class,
                () -> ConsultaValidator.validarPermissao(usuario)
        );

        assertEquals(MensagemUtil.PERMISSAO_NEGADA, exception.getMessage());
    }

    @Test
    @DisplayName("Deve validar campos obrigatórios com sucesso")
    void deveValidarCamposObrigatoriosComSucesso() {
        Usuario paciente = Usuario.builder().id(1).build();
        Usuario medico = Usuario.builder().id(2).build();
        Especialidade especialidade = Especialidade.builder().id(1).build();

        Consulta consulta = Consulta.builder()
                .dataHora(LocalDateTime.now())
                .paciente(paciente)
                .medico(medico)
                .especialidade(especialidade)
                .valor(BigDecimal.valueOf(100.00))
                .build();

        assertDoesNotThrow(() -> ConsultaValidator.validarCamposObrigatorios(consulta));
    }

    @Test
    @DisplayName("Deve lançar exceção quando data/hora for nula")
    void deveLancarExcecaoQuandoDataHoraForNula() {
        Usuario paciente = Usuario.builder().id(1).build();
        Usuario medico = Usuario.builder().id(2).build();
        Especialidade especialidade = Especialidade.builder().id(1).build();

        Consulta consulta = Consulta.builder()
                .paciente(paciente)
                .medico(medico)
                .especialidade(especialidade)
                .valor(BigDecimal.valueOf(100.00))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ConsultaValidator.validarCamposObrigatorios(consulta)
        );

        assertEquals(MensagemUtil.CONSULTA_DATA_HORA_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando paciente for nulo")
    void deveLancarExcecaoQuandoPacienteForNulo() {
        Usuario medico = Usuario.builder().id(2).build();
        Especialidade especialidade = Especialidade.builder().id(1).build();

        Consulta consulta = Consulta.builder()
                .dataHora(LocalDateTime.now())
                .medico(medico)
                .especialidade(especialidade)
                .valor(BigDecimal.valueOf(100.00))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ConsultaValidator.validarCamposObrigatorios(consulta)
        );

        assertEquals(MensagemUtil.CONSULTA_PACIENTE_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando especialidade for nula")
    void deveLancarExcecaoQuandoEspecialidadeForNula() {
        Usuario paciente = Usuario.builder().id(1).build();
        Usuario medico = Usuario.builder().id(2).build();

        Consulta consulta = Consulta.builder()
                .dataHora(LocalDateTime.now())
                .paciente(paciente)
                .medico(medico)
                .valor(BigDecimal.valueOf(100.00))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ConsultaValidator.validarCamposObrigatorios(consulta)
        );

        assertEquals(MensagemUtil.CONSULTA_ESPECIALIDADE_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando médico for nulo")
    void deveLancarExcecaoQuandoMedicoForNulo() {
        Usuario paciente = Usuario.builder().id(1).build();
        Especialidade especialidade = Especialidade.builder().id(1).build();

        Consulta consulta = Consulta.builder()
                .dataHora(LocalDateTime.now())
                .paciente(paciente)
                .especialidade(especialidade)
                .valor(BigDecimal.valueOf(100.00))
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ConsultaValidator.validarCamposObrigatorios(consulta)
        );

        assertEquals(MensagemUtil.CONSULTA_MEDICO_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando valor for nulo")
    void deveLancarExcecaoQuandoValorForNulo() {
        Usuario paciente = Usuario.builder().id(1).build();
        Usuario medico = Usuario.builder().id(2).build();
        Especialidade especialidade = Especialidade.builder().id(1).build();

        Consulta consulta = Consulta.builder()
                .dataHora(LocalDateTime.now())
                .paciente(paciente)
                .medico(medico)
                .especialidade(especialidade)
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ConsultaValidator.validarCamposObrigatorios(consulta)
        );

        assertEquals(MensagemUtil.VALOR_VAZIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve validar sem conflito quando lista de consultas está vazia")
    void deveValidarSemConflitoQuandoListaVazia() {
        Consulta novaConsulta = Consulta.builder()
                .dataHora(LocalDateTime.now())
                .build();

        List<Consulta> consultasVazias = Collections.emptyList();

        assertDoesNotThrow(() -> ConsultaValidator.validarConflitoMedico(novaConsulta, consultasVazias));
    }

    @Test
    @DisplayName("Deve validar sem conflito quando não há conflito de horário")
    void deveValidarSemConflitoQuandoNaoHaConflito() {
        LocalDateTime agora = LocalDateTime.now();
        Consulta novaConsulta = Consulta.builder()
                .dataHora(agora)
                .build();

        Consulta consultaExistente = Consulta.builder()
                .dataHora(agora.plusHours(1))
                .build();

        List<Consulta> consultas = List.of(consultaExistente);

        assertDoesNotThrow(() -> ConsultaValidator.validarConflitoMedico(novaConsulta, consultas));
    }

    @Test
    @DisplayName("Deve lançar exceção quando há conflito de horário")
    void deveLancarExcecaoQuandoHaConflito() {
        LocalDateTime agora = LocalDateTime.now();
        Consulta novaConsulta = Consulta.builder()
                .dataHora(agora)
                .build();

        Consulta consultaExistente = Consulta.builder()
                .dataHora(agora.plusMinutes(15))
                .build();

        List<Consulta> consultas = List.of(consultaExistente);

        NotificacaoException exception = assertThrows(
                NotificacaoException.class,
                () -> ConsultaValidator.validarConflitoMedico(novaConsulta, consultas)
        );

        assertEquals(MensagemUtil.CONSULTA_CONFLITO_HORARIO, exception.getMessage());
    }

    @Test
    @DisplayName("Deve validar sem conflito quando diferença é exatamente 30 minutos")
    void deveValidarSemConflitoQuandoDiferencaEh30Minutos() {
        LocalDateTime agora = LocalDateTime.now();
        Consulta novaConsulta = Consulta.builder()
                .dataHora(agora)
                .build();

        Consulta consultaExistente = Consulta.builder()
                .dataHora(agora.plusMinutes(30))
                .build();

        List<Consulta> consultas = List.of(consultaExistente);

        assertDoesNotThrow(() -> ConsultaValidator.validarConflitoMedico(novaConsulta, consultas));
    }
}
