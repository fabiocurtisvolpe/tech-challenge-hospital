package com.adjt.pagamento.core.usecase;

import com.adjt.pagamento.core.exception.NotificacaoException;
import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.port.PagamentoPort;
import com.adjt.pagamento.core.util.MensagemUtil;

import java.util.Objects;

public class ObterPagamentoUseCase {

    private final PagamentoPort<Pagamento> pagamentoPort;

    private ObterPagamentoUseCase(PagamentoPort<Pagamento> pagamentoPort) {
        this.pagamentoPort = pagamentoPort;
    }

    public static ObterPagamentoUseCase create(PagamentoPort<Pagamento> pagamentoPort) {
        return new ObterPagamentoUseCase(pagamentoPort);
    }

    public Pagamento run(Integer id) {

        Pagamento pgto = this.pagamentoPort.obterPorId(id);

        if (Objects.isNull(pgto)) {
            throw new NotificacaoException(MensagemUtil.PGTO_NAO_ENCONTRADO);
        }

        return pgto;
    }
}
