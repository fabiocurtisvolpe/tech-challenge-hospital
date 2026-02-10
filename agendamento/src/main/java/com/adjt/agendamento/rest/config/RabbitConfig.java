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
    public static final String EXCHANGE_AGENDAMENTO = "exchange.agendamento";
    public static final String ROUTING_KEY_PAGAMENTO_RESULTADO = "routing.pagamento.resultado";
    public static final String ROUTING_KEY_NOTIFICACAO_RESULTADO = "routing.notificacao.resultado";

    public static final String EXCHANGE_CONSULTA = "exchange.consulta";
    public static final String ROUTING_KEY_CONSULTA_CRIADA = "routing.consulta.criada";

    public static final String EXCHANGE_NOTIFICACAO = "exchange.notificacao";
    public static final String ROUTING_KEY_NOTIFICACAO_ENVIAR = "routing.notificacao.enviar";
    public static final String QUEUE_NOTIFICACAO_ATUALIZAR = "queue.notificacao.atualizar";

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queueAtualizar() {
        return new Queue(QUEUE_AGENDAMENTO_ATUALIZAR, true);
    }

    @Bean
    public DirectExchange exchangePagamento() {
        return new DirectExchange(EXCHANGE_AGENDAMENTO);
    }

    @Bean
    public Binding bindingAtualizar(Queue queueAtualizar, DirectExchange exchangePagamento) {
        return BindingBuilder.bind(queueAtualizar)
                .to(exchangePagamento)
                .with(ROUTING_KEY_PAGAMENTO_RESULTADO);
    }

    @Bean
    public Binding bindingNotificacaoAtualizar(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_NOTIFICACAO_RESULTADO);
    }
}