package com.adjt.notificacao.core.port;

import java.util.List;

public interface NotificacaoPort<Notificacao> {

    Notificacao criar(Notificacao model);
    Notificacao atualizar(Notificacao model);
    Boolean excluir(Integer id);
    Notificacao obterPorId(Integer id);
    List<Notificacao> obterTodas();
}
