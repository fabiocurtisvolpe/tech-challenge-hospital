package com.adjt.historico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EntityScan({
        "com.adjt.historico.data.entity"
})
@EnableJpaRepositories("com.adjt.historico.data.repository")
public class HistoricoApplication {
    static void main(String[] args) {
        SpringApplication.run(HistoricoApplication.class, args);
    }
}