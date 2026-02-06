package com.adjt.pagamento.core.usecase;

import com.adjt.pagamento.core.exception.NotificacaoException;
import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.port.PagamentoPort;
import com.adjt.pagamento.core.util.MensagemUtil;
import com.adjt.pagamento.core.validator.PagamentoValidator;

import java.time.LocalDateTime;
import java.util.Objects;

public class AtualizarPagamentoUseCase {

    private final PagamentoPort<Pagamento> pagamentoPort;

    private AtualizarPagamentoUseCase(PagamentoPort<Pagamento> pagamentoPort) {
        this.pagamentoPort = pagamentoPort;
    }

    public static AtualizarPagamentoUseCase create(PagamentoPort<Pagamento> pagamentoPort) {
        return new AtualizarPagamentoUseCase(pagamentoPort);
    }

    public Pagamento run(Integer id, Pagamento model) {

        Pagamento pgto = this.pagamentoPort.obterPorId(id);

        if (Objects.isNull(pgto)) {
            throw new NotificacaoException(MensagemUtil.PGTO_NAO_ENCONTRADO);
        }

        Pagamento atualizarPgto = Pagamento.builder()
                .id(pgto.getId())
                .dataHora(LocalDateTime.now())
                .idConsulta(pgto.getIdConsulta())
                .idPaciente(pgto.getIdPaciente())
                .responseCode(pgto.getResponseCode())
                .valor(model.getValor())
                .build();

        PagamentoValidator.cadastarAtualizar(atualizarPgto);
        return this.pagamentoPort.atualizar(atualizarPgto);
    }
}
