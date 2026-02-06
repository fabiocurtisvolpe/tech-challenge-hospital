package com.adjt.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableCaching
@EnableRetry
public class PagamentoApplication {
    static void main(String[] args) {
        SpringApplication.run(PagamentoApplication.class, args);
    }
}