package com.adjt.pagamento.rest.config;

import com.adjt.pagamento.core.model.Pagamento;
import com.adjt.pagamento.core.port.PagamentoPort;
import com.adjt.pagamento.core.usecase.AtualizarPagamentoErroUseCase;
import com.adjt.pagamento.core.usecase.AtualizarPagamentoSucessoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public AtualizarPagamentoSucessoUseCase atualizarPagamentoSucessoUseCase(PagamentoPort<Pagamento> pagamentoPort) {
        return AtualizarPagamentoSucessoUseCase.create(pagamentoPort);
    }

    @Bean
    public AtualizarPagamentoErroUseCase atualizarPagamentoErroUseCase(PagamentoPort<Pagamento> pagamentoPort) {
        return AtualizarPagamentoErroUseCase.create(pagamentoPort);
    }
}
