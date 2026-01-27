package com.adjt.notificacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EntityScan({
        "com.adjt.notificacao.data.entity"
})
@EnableJpaRepositories("com.adjt.notificacao.data.repository")
public class NotificacaoApplication {
    static void main(String[] args) {
        SpringApplication.run(NotificacaoApplication.class, args);
    }
}