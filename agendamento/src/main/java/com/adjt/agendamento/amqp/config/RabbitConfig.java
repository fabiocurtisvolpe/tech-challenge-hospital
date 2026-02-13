package com.adjt.agendamento.amqp.config;

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
    public static final String EXCHANGE_PAGAMENTO = "pagamento.events.ex";
    public static final String EXCHANGE_NOTIFICACAO = "notificacao.events.ex";

    public static final String QUEUE_AGENDAMENTO_ATUALIZAR = "queue.agendamento.atualizar";
    public static final String QUEUE_NOTIFICACAO_ATUALIZAR = "queue.notificacao.atualizar";

    public static final String ROUTING_KEY_PAGAMENTO_RESULTADO = "routing.pagamento.resultado";
    public static final String ROUTING_KEY_NOTIFICACAO_RESULTADO = "routing.notificacao.resultado";

    public static final String ROUTING_KEY_CONSULTA_CRIADA = "routing.consulta.criada";
    public static final String ROUTING_KEY_NOTIFICACAO_ENVIAR = "routing.notificacao.enviar";

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Fila para resultados de Pagamento
    @Bean
    public Queue queueAgendamentoAtualizar() {
        return new Queue(QUEUE_AGENDAMENTO_ATUALIZAR, true);
    }

    // Fila para resultados de Notificação
    @Bean
    public Queue queueNotificacaoAtualizar() {
        return new Queue(QUEUE_NOTIFICACAO_ATUALIZAR, true);
    }

    // Pagamento -> Agendamento
    @Bean
    public Binding bindingPagamentoAtualizar(Queue queueAgendamentoAtualizar, DirectExchange exchangeAgendamento) {
        return BindingBuilder.bind(queueAgendamentoAtualizar)
                .to(exchangeAgendamento)
                .with(ROUTING_KEY_PAGAMENTO_RESULTADO);
    }

    // Notificação -> Agendamento
    @Bean
    public Binding bindingNotificacaoAtualizar(Queue queueNotificacaoAtualizar, DirectExchange exchangeAgendamento) {
        return BindingBuilder.bind(queueNotificacaoAtualizar)
                .to(exchangeAgendamento)
                .with(ROUTING_KEY_NOTIFICACAO_RESULTADO);
    }

    // Exchange para enviar para o serviço de Agendamento
    @Bean
    public DirectExchange exchangeAgendamento() {
        return new DirectExchange(EXCHANGE_AGENDAMENTO);
    }

    // Exchange para enviar para o serviço de Notificação
    @Bean
    public DirectExchange exchangeNotificacao() {
        return new DirectExchange(EXCHANGE_NOTIFICACAO);
    }

    // Exchange para enviar para o serviço de Consulta
    @Bean
    public DirectExchange exchangeConsulta() {
        return new DirectExchange(EXCHANGE_PAGAMENTO);
    }
}