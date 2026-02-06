package com.adjt.pagamento.core.usecase;

import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.port.PagamentoPort;

import java.util.List;

public class ObterPagamentoFalhou {

    private final PagamentoPort<Pagamento> pagamentoPort;

    private ObterPagamentoFalhou(PagamentoPort<Pagamento> pagamentoPort) {
        this.pagamentoPort = pagamentoPort;
    }

    public static ObterPagamentoFalhou create(PagamentoPort<Pagamento> pagamentoPort) {
        return new ObterPagamentoFalhou(pagamentoPort);
    }

    public List<Pagamento> run() {
        return this.pagamentoPort.obterPorFalhou();
    }
}
