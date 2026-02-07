package com.adjt.agendamento.core.usecase.consulta;

import com.adjt.agendamento.core.enums.StatusPagamentoEnum;
import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.port.ConsultaPort;

public class AtualizarStatusPagamentoConsultaUseCase {

    private final ConsultaPort<Consulta> consultaPort;

    private AtualizarStatusPagamentoConsultaUseCase(ConsultaPort<Consulta> consultaPort) {
        this.consultaPort = consultaPort;
    }

    public static AtualizarStatusPagamentoConsultaUseCase create(ConsultaPort<Consulta> consultaPort) {
        return new AtualizarStatusPagamentoConsultaUseCase(consultaPort);
    }

    public Consulta run(Integer consultaId, StatusPagamentoEnum status) {

        Consulta consulta = this.consultaPort.obterPorId(consultaId);

        Consulta atualizarConsulta = Consulta.builder()
                .id(consulta.getId())
                .dataHora(consulta.getDataHora())
                .diagnostico(consulta.getDiagnostico())
                .prescricao(consulta.getPrescricao())
                .paciente(consulta.getPaciente())
                .medico(consulta.getMedico())
                .enfermeiro(consulta.getEnfermeiro())
                .especialidade(consulta.getEspecialidade())
                .valor(consulta.getValor())
                .status(status)
                .build();

        return consultaPort.atualizar(atualizarConsulta);
    }
}
