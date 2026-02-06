package com.adjt.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EntityScan({
        "com.adjt.pagamento.data.entity"
})
@EnableJpaRepositories("com.adjt.pagamento.data.repository")
public class NotificacaoApplication {
    static void main(String[] args) {
        SpringApplication.run(NotificacaoApplication.class, args);
    }
}