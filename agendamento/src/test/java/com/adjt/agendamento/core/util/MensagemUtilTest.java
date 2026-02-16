package com.adjt.agendamento.core.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MensagemUtilTest {

    @Test
    @DisplayName("Deve ter mensagem de id vazio")
    void deveTerMensagemIdVazio() {
        assertEquals("O id não pode ser vazio.", MensagemUtil.ID_VAZIO);
    }

    @Test
    @DisplayName("Deve ter mensagem de página e size inválidos")
    void deveTerMensagemPaginaSizeInvalida() {
        assertEquals("Página e tamanho devem ser positivos.", MensagemUtil.PAGINA_SIZE_INVALIDA);
    }

    @Test
    @DisplayName("Deve ter mensagem de operação não possível")
    void deveTerMensagemOperacaoNaoPossivel() {
        assertEquals("Não foi possível executar a operação.", MensagemUtil.NAO_FOI_POSSIVEL_EXECUTAR_OPERACAO);
    }

    @Test
    @DisplayName("Deve ter mensagem de consulta não encontrada")
    void deveTerMensagemConsultaNaoEncontrada() {
        assertEquals("Consulta não encontrada.", MensagemUtil.CONSULTA_NAO_ENCONTRADA);
    }

    @Test
    @DisplayName("Deve ter mensagem de especialidade não encontrada")
    void deveTerMensagemEspecialidadeNaoEncontrada() {
        assertEquals("Especialidade não encontrada.", MensagemUtil.ESPECIALIDADE_NAO_ENCONTRADA);
    }

    @Test
    @DisplayName("Deve ter mensagem de perfil não encontrado")
    void deveTerMensagemPerfilNaoEncontrado() {
        assertEquals("Perfil não encontrado.", MensagemUtil.PERFIL_NAO_ENCONTRADO);
    }

    @Test
    @DisplayName("Deve ter mensagem de usuário não encontrado")
    void deveTerMensagemUsuarioNaoEncontrado() {
        assertEquals("Usuário não encontrado.", MensagemUtil.USUARIO_NAO_ENCONTRADO);
    }

    @Test
    @DisplayName("Deve ter mensagem de usuário logado não encontrado")
    void deveTerMensagemUsuarioLogadoNaoEncontrado() {
        assertEquals("Usuário logado não encontrado.", MensagemUtil.USUARIO_LOGADO_NAO_ENCONTRADO);
    }

    @Test
    @DisplayName("Deve ter mensagem de permissão negada")
    void deveTerMensagemPermissaoNegada() {
        assertEquals("Usuário sem permissão.", MensagemUtil.PERMISSAO_NEGADA);
    }

    @Test
    @DisplayName("Deve ter mensagem de nome vazio")
    void deveTerMensagemNomeVazio() {
        assertEquals("O nome não pode ser vazio.", MensagemUtil.USUARIO_NOME_VAZIO);
    }

    @Test
    @DisplayName("Deve ter mensagem de telefone inválido")
    void deveTerMensagemTelefoneInvalido() {
        assertEquals("Telefone inválido.", MensagemUtil.USUARIO_TELEFONE_INVALIDO);
    }

    @Test
    @DisplayName("Deve ter mensagem de email inválido")
    void deveTerMensagemEmailInvalido() {
        assertEquals("E-mail inválido.", MensagemUtil.USUARIO_EMAIL_INVALIDO);
    }

    @Test
    @DisplayName("Deve ter mensagem de senha vazia")
    void deveTerMensagemSenhaVazia() {
        assertEquals("A senha não pode ser vazia.", MensagemUtil.USUARIO_SENHA_VAZIO);
    }

    @Test
    @DisplayName("Deve ter mensagem de perfil vazio")
    void deveTerMensagemPerfilVazio() {
        assertEquals("O usuário deve ter pelo menos um perfil.", MensagemUtil.USUARIO_PERFIL_VAZIO);
    }

    @Test
    @DisplayName("Deve ter mensagem de perfil inválido")
    void deveTerMensagemPerfilInvalido() {
        assertEquals("O usuário deve possuir um perfil válido", MensagemUtil.USUARIO_PERFIL_INVALIDO);
    }

    @Test
    @DisplayName("Deve ter mensagem de data/hora vazia")
    void deveTerMensagemDataHoraVazia() {
        assertEquals("A data e hora não pode ser vazia.", MensagemUtil.CONSULTA_DATA_HORA_VAZIO);
    }

    @Test
    @DisplayName("Deve ter mensagem de paciente vazio")
    void deveTerMensagemPacienteVazio() {
        assertEquals("O paciente não pode ser vazio.", MensagemUtil.CONSULTA_PACIENTE_VAZIO);
    }

    @Test
    @DisplayName("Deve ter mensagem de especialidade vazia")
    void deveTerMensagemEspecialidadeVazia() {
        assertEquals("A especialidade não pode ser vazia.", MensagemUtil.CONSULTA_ESPECIALIDADE_VAZIO);
    }

    @Test
    @DisplayName("Deve ter mensagem de médico vazio")
    void deveTerMensagemMedicoVazio() {
        assertEquals("O médico não pode ser vazio.", MensagemUtil.CONSULTA_MEDICO_VAZIO);
    }

    @Test
    @DisplayName("Deve ter mensagem de conflito de horário")
    void deveTerMensagemConflitoHorario() {
        assertEquals("O médico já possui uma consulta em um intervalo menor que 30 minutos.", MensagemUtil.CONSULTA_CONFLITO_HORARIO);
    }

    @Test
    @DisplayName("Deve ter mensagem de valor vazio")
    void deveTerMensagemValorVazio() {
        assertEquals("O valor do pagamento não pode ser vazio.", MensagemUtil.VALOR_VAZIO);
    }
}
