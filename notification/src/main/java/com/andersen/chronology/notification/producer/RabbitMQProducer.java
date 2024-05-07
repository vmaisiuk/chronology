package com.andersen.chronology.notification.producer;

import com.andersen.chronology.notification.dto.NotificationSendResponse;
import com.andersen.chronology.notification.mapper.NotificationMapper;
import com.andersen.chronology.notification.properties.RabbitMQProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

@Service
@Slf4j
@AllArgsConstructor
public class RabbitMQProducer {

    private final Sender sender;
    private final RabbitMQProperties rabbitMQProperties;
    private final NotificationMapper mapper;

    public void sendEvent(NotificationSendResponse request) {
        sender.send(getMessage(request))
                .doOnError(e -> log.error("Can't send a message with id {}", request.getNotificationId()))
                .subscribe();
    }

    private Mono<OutboundMessage> getMessage(NotificationSendResponse request) {
        return Mono.just(new OutboundMessage(
                "",
                rabbitMQProperties.getNotificationStatusQueueName(), mapper.toByteArray(request)
        ));
    }
}