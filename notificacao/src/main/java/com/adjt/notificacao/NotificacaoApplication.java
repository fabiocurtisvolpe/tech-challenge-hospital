package com.adjt.notificacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableCaching
@EnableRetry
public class NotificacaoApplication {
    static void main(String[] args) {
        SpringApplication.run(NotificacaoApplication.class, args);
    }
}