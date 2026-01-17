package com.adjt.agendamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EntityScan({
        "com.adjt.agendamento.data.entity"
})
@EnableJpaRepositories("com.adjt.agendamento.data.repository")
public class AgendamentoApplication {
    static void main(String[] args) {
        SpringApplication.run(AgendamentoApplication.class, args);
    }
}