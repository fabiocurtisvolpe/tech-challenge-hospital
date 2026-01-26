package com.adjt.agendamento.core.usecase.consulta;

import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.ConsultaPort;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.ConsultaValidator;

public class ObterPorIdConsultaUseCase {

    private final ConsultaPort<Consulta> consultaPort;
    private final UsuarioPort<Usuario> usuarioPort;

    private ObterPorIdConsultaUseCase(ConsultaPort<Consulta> consultaPort,
                                      UsuarioPort<Usuario> usuarioPort) {
        this.consultaPort = consultaPort;
        this.usuarioPort = usuarioPort;
    }

    public static ObterPorIdConsultaUseCase create(ConsultaPort<Consulta> consultaPort,
                                                   UsuarioPort<Usuario> usuarioPort) {
        return new ObterPorIdConsultaUseCase(consultaPort, usuarioPort);
    }

    public Consulta run(Integer id, String usuarioLogado) {

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);
        ConsultaValidator.validarPermissao(usrLogado);
        return consultaPort.obterPorId(id);
    }
}
