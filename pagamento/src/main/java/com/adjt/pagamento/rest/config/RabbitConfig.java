package com.adjt.pagamento.rest.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_PAGAMENTO_PROCESSAR = "queue.pagamento.processar";
    public static final String EXCHANGE_CONSULTA = "pagamento.events.ex";
    public static final String ROUTING_KEY_CRIADA = "routing.consulta.criada";

    public static final String EXCHANGE_AGENDAMENTO = "agendamento.events.ex";
    public static final String ROUTING_KEY_RESULTADO = "routing.pagamento.resultado";

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queuePagamento() {
        return new Queue(QUEUE_PAGAMENTO_PROCESSAR, true);
    }

    @Bean
    public DirectExchange exchangeConsulta() {
        return new DirectExchange(EXCHANGE_CONSULTA);
    }

    @Bean
    public Binding bindingPagamento(Queue queuePagamento, DirectExchange exchangeConsulta) {
        return BindingBuilder.bind(queuePagamento)
                .to(exchangeConsulta)
                .with(ROUTING_KEY_CRIADA);
    }
}