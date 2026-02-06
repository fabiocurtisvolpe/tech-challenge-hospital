package com.adjt.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PagamentoApplication {
    static void main(String[] args) {
        SpringApplication.run(PagamentoApplication.class, args);
    }
}