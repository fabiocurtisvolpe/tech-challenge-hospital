package com.adjt.pagamento.core.port;

import java.util.List;

public interface PagamentoPort<Pagamento> {

    Pagamento criar(Pagamento model);
    Pagamento atualizar(Pagamento model);
    Pagamento obterPorContultaId(Integer id);
    List<Pagamento> obterPorFalhou();
}
