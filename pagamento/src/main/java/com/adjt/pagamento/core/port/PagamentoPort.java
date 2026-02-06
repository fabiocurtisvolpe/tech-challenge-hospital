package com.adjt.pagamento.core.port;

public interface PagamentoPort<Pagamento> {

    Pagamento criar(Pagamento model);
    Pagamento atualizar(Pagamento model);
    Pagamento obterPorId(Integer id);
}
