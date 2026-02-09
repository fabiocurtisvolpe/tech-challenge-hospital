package com.adjt.agendamento.rest.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_AGENDAMENTO_ATUALIZAR = "queue.agendamento.atualizar";
    public static final String EXCHANGE_PAGAMENTO = "exchange.pagamento";
    public static final String ROUTING_KEY_RESULTADO = "routing.pagamento.resultado";

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queueAtualizar() {
        // durable: true (a fila sobrevive se o RabbitMQ reiniciar)
        return new Queue(QUEUE_AGENDAMENTO_ATUALIZAR, true);
    }

    @Bean
    public DirectExchange exchangePagamento() {
        return new DirectExchange(EXCHANGE_PAGAMENTO);
    }

    @Bean
    public Binding bindingAtualizar(Queue queueAtualizar, DirectExchange exchangePagamento) {
        return BindingBuilder.bind(queueAtualizar)
                .to(exchangePagamento)
                .with(ROUTING_KEY_RESULTADO);
    }
}