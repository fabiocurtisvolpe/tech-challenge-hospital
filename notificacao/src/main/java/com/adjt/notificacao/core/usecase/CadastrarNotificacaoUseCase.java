package com.adjt.notificacao.core.usecase;

import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.model.Especialidade;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.ConsultaPort;
import com.adjt.agendamento.core.port.EspecialidadePort;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.ConsultaValidator;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CadastrarNotificacaoUseCase {

    private final ConsultaPort<Consulta> consultaPort;
    private final UsuarioPort<Usuario> usuarioPort;
    private final EspecialidadePort<Especialidade> especialidadePort;

    private CadastrarNotificacaoUseCase(ConsultaPort<Consulta> consultaPort,
                                        UsuarioPort<Usuario> usuarioPort,
                                        EspecialidadePort<Especialidade> especialidadePort) {
        this.consultaPort = consultaPort;
        this.usuarioPort = usuarioPort;
        this.especialidadePort = especialidadePort;
    }

    public static CadastrarNotificacaoUseCase create(ConsultaPort<Consulta> consultaPort,
                                                     UsuarioPort<Usuario> usuarioPort,
                                                     EspecialidadePort<Especialidade> especialidadePort) {
        return new CadastrarNotificacaoUseCase(consultaPort, usuarioPort, especialidadePort);
    }

    public Consulta run(Consulta consulta,
                        Integer pacienteId,
                        Integer medicadoId,
                        Integer enfermeiroId,
                        Integer especialidadeId,
                        String usuarioLogado) {

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);
        ConsultaValidator.validarPermissao(usrLogado);

        Usuario enfermeiro = null;

        Usuario paciente = this.usuarioPort.obterPorId(pacienteId);
        Usuario medico = this.usuarioPort.obterPorId(medicadoId);
        Especialidade especialidade = this.especialidadePort.obterPorId(especialidadeId);

        if (enfermeiroId != null) {
            try {
                enfermeiro = this.usuarioPort.obterPorId(enfermeiroId);
            } catch (EntityNotFoundException _) {
            }
        }

        if (Objects.isNull(enfermeiro)) {
           if (usrLogado.isEnfermeiro()) {
               enfermeiro = usrLogado;
           }
        }

        Consulta novaConsulta = Consulta.builder()
                .dataHora(consulta.getDataHora())
                .diagnostico(consulta.getDiagnostico())
                .prescricao(consulta.getPrescricao())
                .paciente(paciente)
                .medico(medico)
                .enfermeiro(enfermeiro)
                .especialidade(especialidade)
                .build();

        LocalDateTime inicioDia = novaConsulta.getDataHora().toLocalDate().atStartOfDay();
        LocalDateTime fimDia = novaConsulta.getDataHora().toLocalDate().atTime(23, 59, 59);

        List<Consulta> consultasDoDia = consultaPort.listarPorMedicoEIntervalo(
                novaConsulta.getMedico().getId(),
                inicioDia,
                fimDia
        );

        ConsultaValidator.validarCamposObrigatorios(novaConsulta);
        ConsultaValidator.validarConflitoMedico(novaConsulta, consultasDoDia);

        return consultaPort.criar(novaConsulta);
    }
}
