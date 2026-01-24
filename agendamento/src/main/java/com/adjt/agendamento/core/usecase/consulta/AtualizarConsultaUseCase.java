package com.adjt.agendamento.core.usecase.consulta;

import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.ConsultaPort;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.ConsultaValidator;

public class AtualizarConsultaUseCase {

    private final ConsultaPort<Consulta> consultaPort;
    private final UsuarioPort<Usuario> usuarioPort;

    private AtualizarConsultaUseCase(ConsultaPort<Consulta> consultaPort,
                                     UsuarioPort<Usuario> usuarioPort) {
        this.consultaPort = consultaPort;
        this.usuarioPort = usuarioPort;
    }

    public static AtualizarConsultaUseCase create(ConsultaPort<Consulta> consultaPort,
                                                  UsuarioPort<Usuario> usuarioPort) {
        return new AtualizarConsultaUseCase(consultaPort, usuarioPort);
    }

    public Consulta run(Consulta consulta, String usuarioLogado) {

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);

        ConsultaValidator.validarPermissao(usrLogado);
        ConsultaValidator.validarId(consulta);
        ConsultaValidator.validarCamposObrigatorios(consulta);

        return consultaPort.atualizar(consulta);
    }
}
