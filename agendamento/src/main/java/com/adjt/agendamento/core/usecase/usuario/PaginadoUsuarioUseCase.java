package com.adjt.agendamento.core.usecase.usuario;

import com.adjt.agendamento.core.dto.ResultadoPaginacaoDTO;
import com.adjt.agendamento.core.dto.filtro.FilterDTO;
import com.adjt.agendamento.core.dto.filtro.SortDTO;
import com.adjt.agendamento.core.exception.NotificacaoException;
import com.adjt.agendamento.core.model.Usuario;
import com.adjt.agendamento.core.port.UsuarioPort;
import com.adjt.agendamento.core.util.MensagemUtil;
import com.adjt.agendamento.core.util.UsuarioLogadoUtil;
import com.adjt.agendamento.core.validator.UsuarioValidator;

import java.util.List;

public class PaginadoUsuarioUseCase<T> {

    private final UsuarioPort<Usuario> usuarioPort;

    private PaginadoUsuarioUseCase(UsuarioPort<Usuario> usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    public static <T> PaginadoUsuarioUseCase<T> create(UsuarioPort<Usuario> usuarioPort) {
        return new PaginadoUsuarioUseCase<>(usuarioPort);
    }

    public ResultadoPaginacaoDTO<Usuario> run(int page, int size, List<FilterDTO> filters, List<SortDTO> sorts, String usuarioLogado) {

        if (page < 0 || size <= 0) {
            throw new NotificacaoException(MensagemUtil.PAGINA_SIZE_INVALIDA);
        }

        final Usuario usrLogado = UsuarioLogadoUtil.usuarioLogado(usuarioPort, usuarioLogado);
        UsuarioValidator.validarPermissaoBuscar(usrLogado);

        return usuarioPort.listarPaginado(page, size, filters, sorts);
    }
}