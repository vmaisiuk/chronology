package com.andersen.chronology.rabbit.config;

import com.rabbitmq.client.ConnectionFactory;
import lombok.AllArgsConstructor;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Schedulers;
import reactor.rabbitmq.*;

@Configuration
@AllArgsConstructor
public class RabbitMQConfig {

    private final RabbitProperties rabbitProperties;

    @Bean
    public ConnectionFactory rabbitMQConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitProperties.getHost());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        connectionFactory.useNio();

        return connectionFactory;
    }

    @Bean
    public Receiver rabbitMQReceiver(ConnectionFactory connectionFactory) {
        return RabbitFlux.createReceiver(
                new ReceiverOptions()
                        .connectionFactory(connectionFactory)
                        .connectionSubscriptionScheduler(Schedulers.boundedElastic())
        );
    }

    @Bean
    public Sender rabbitMQSender(ConnectionFactory connectionFactory) {
        return RabbitFlux.createSender(
                new SenderOptions()
                        .connectionFactory(connectionFactory)
                        .resourceManagementScheduler(Schedulers.boundedElastic())
        );
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
