package com.adjt.pagamento.core.usecase;

import com.adjt.pagamento.core.enums.StatusPagamentoEnum;
import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.port.PagamentoPort;
import com.adjt.pagamento.core.validator.PagamentoValidator;

import java.time.LocalDateTime;

public class CadastrarPagamentoUseCase {

    private final PagamentoPort<Pagamento> pagamentoPort;

    private CadastrarPagamentoUseCase(PagamentoPort<Pagamento> pagamentoPort) {
        this.pagamentoPort = pagamentoPort;
    }

    public static CadastrarPagamentoUseCase create(PagamentoPort<Pagamento> pagamentoPort) {
        return new CadastrarPagamentoUseCase(pagamentoPort);
    }

    public void run(Pagamento model) {

        Pagamento novoPagamento = Pagamento.builder()
                .dataHora(LocalDateTime.now())
                .idConsulta(model.getIdConsulta())
                .idPaciente(model.getIdPaciente())
                .valor(model.getValor())
                .status(StatusPagamentoEnum.PENDENTE_PAGAMENTO)
                .build();

        PagamentoValidator.cadastarAtualizar(novoPagamento);
        this.pagamentoPort.criar(novoPagamento);
    }
}
