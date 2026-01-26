package com.adjt.agendamento.core.usecase.consulta;

import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.model.Especialidade;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.ConsultaPort;
import com.adjt.agendamento.core.port.EspecialidadePort;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.ConsultaValidator;
import jakarta.persistence.EntityNotFoundException;

import java.util.Objects;

public class CadastrarConsultaUseCase {

    private final ConsultaPort<Consulta> consultaPort;
    private final UsuarioPort<Usuario> usuarioPort;
    private final EspecialidadePort<Especialidade> especialidadePort;

    private CadastrarConsultaUseCase(ConsultaPort<Consulta> consultaPort,
                                     UsuarioPort<Usuario> usuarioPort,
                                     EspecialidadePort<Especialidade> especialidadePort) {
        this.consultaPort = consultaPort;
        this.usuarioPort = usuarioPort;
        this.especialidadePort = especialidadePort;
    }

    public static CadastrarConsultaUseCase create(ConsultaPort<Consulta> consultaPort,
                                                  UsuarioPort<Usuario> usuarioPort,
                                                  EspecialidadePort<Especialidade> especialidadePort) {
        return new CadastrarConsultaUseCase(consultaPort, usuarioPort, especialidadePort);
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

        ConsultaValidator.validarCamposObrigatorios(novaConsulta);

        return consultaPort.criar(novaConsulta);
    }
}
