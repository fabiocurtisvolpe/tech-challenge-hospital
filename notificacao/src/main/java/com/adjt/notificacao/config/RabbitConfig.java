package com.adjt.notificacao.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_AGENDAMENTO = "agendamento.events.ex";
    public static final String EXCHANGE_NOTIFICACAO = "notificacao.events.ex";

    public static final String QUEUE_NOTIFICACAO_PROCESSAR = "queue.notificacao.processar";

    public static final String ROUTING_KEY_ENVIAR = "routing.notificacao.enviar";
    public static final String ROUTING_KEY_RESULTADO = "routing.notificacao.resultado";

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queueNotificacao() {
        return new Queue(QUEUE_NOTIFICACAO_PROCESSAR, true);
    }

    @Bean
    public DirectExchange exchangeNotificacao() {
        return new DirectExchange(EXCHANGE_NOTIFICACAO);
    }

    @Bean
    public Binding bindingNotificacao(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_ENVIAR);
    }
}