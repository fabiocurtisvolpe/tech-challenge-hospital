package com.adjt.pagamento.core.usecase;

import com.adjt.pagamento.core.exception.NotificacaoException;
import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.port.PagamentoPort;
import com.adjt.pagamento.core.util.MensagemUtil;
import com.adjt.pagamento.core.validator.PagamentoValidator;

import java.time.LocalDateTime;
import java.util.Objects;

public class AtualizarPagamentoErroUseCase {

    private final PagamentoPort<Pagamento> pagamentoPort;

    private AtualizarPagamentoErroUseCase(PagamentoPort<Pagamento> pagamentoPort) {
        this.pagamentoPort = pagamentoPort;
    }

    public static AtualizarPagamentoErroUseCase create(PagamentoPort<Pagamento> pagamentoPort) {
        return new AtualizarPagamentoErroUseCase(pagamentoPort);
    }

    public void run(Integer id, int codigoErro) {

        Pagamento pgto = this.pagamentoPort.obterPorContultaId(id);

        if (Objects.isNull(pgto)) {
            throw new NotificacaoException(MensagemUtil.PGTO_NAO_ENCONTRADO);
        }

        Pagamento atualizarPgto = Pagamento.builder()
                .id(pgto.getId())
                .dataHora(LocalDateTime.now())
                .idConsulta(pgto.getIdConsulta())
                .idPaciente(pgto.getIdPaciente())
                .responseCode(codigoErro)
                .valor(pgto.getValor())
                .build();

        PagamentoValidator.cadastarAtualizar(atualizarPgto);
        this.pagamentoPort.atualizar(atualizarPgto);
    }
}
