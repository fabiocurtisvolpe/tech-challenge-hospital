package com.adjt.pagamento.amqp.config;

import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.port.PagamentoPort;
import com.adjt.pagamento.core.usecase.AtualizarPagamentoErroUseCase;
import com.adjt.pagamento.core.usecase.AtualizarPagamentoSucessoUseCase;
import com.adjt.pagamento.core.usecase.CadastrarPagamentoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CadastrarPagamentoUseCase cadastrarPagamentoUseCase(PagamentoPort<Pagamento> pagamentoPort) {
        return CadastrarPagamentoUseCase.create(pagamentoPort);
    }

    @Bean
    public AtualizarPagamentoSucessoUseCase atualizarPagamentoSucessoUseCase(PagamentoPort<Pagamento> pagamentoPort) {
        return AtualizarPagamentoSucessoUseCase.create(pagamentoPort);
    }

    @Bean
    public AtualizarPagamentoErroUseCase atualizarPagamentoErroUseCase(PagamentoPort<Pagamento> pagamentoPort) {
        return AtualizarPagamentoErroUseCase.create(pagamentoPort);
    }
}
