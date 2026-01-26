package com.adjt.agendamento.core.usecase.consulta;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.model.Consulta;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.ConsultaPort;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.PaginadoValidator;
import com.adjt.agendamento.core.validator.UsuarioValidator;

import java.util.List;

public class PaginadoConsultaUseCase<T> {

    private final ConsultaPort<Consulta> consultaPort;
    private final UsuarioPort<Usuario> usuarioPort;

    private PaginadoConsultaUseCase(ConsultaPort<Consulta> consultaPort,
                                    UsuarioPort<Usuario> usuarioPort) {
        this.consultaPort = consultaPort;
        this.usuarioPort = usuarioPort;
    }

    public static <T> PaginadoConsultaUseCase<T> create(ConsultaPort<Consulta> consultaPort,
                                                        UsuarioPort<Usuario> usuarioPort) {
        return new PaginadoConsultaUseCase<>(consultaPort, usuarioPort);
    }

    public ResultadoPaginacaoDTO<Consulta> run(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts, String usuarioLogado) {

        PaginadoValidator.validarPagina(page, size);

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);
        UsuarioValidator.validarPermissaoBuscar(usrLogado);

        return consultaPort.listarPaginado(page, size, filters, sorts);
    }
}