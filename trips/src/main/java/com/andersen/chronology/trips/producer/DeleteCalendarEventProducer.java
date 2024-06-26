package com.andersen.chronology.trips.producer;

import com.andersen.chronology.rabbit.dto.notification.NotificationDeleteCalendarEventRequest;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;
import com.andersen.chronology.rabbit.mapper.NotificationMapper;
import com.andersen.chronology.trips.properties.RabbitMQProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

@Service
@Slf4j
@AllArgsConstructor
public class DeleteCalendarEventProducer {

    private final Sender sender;
    private final RabbitMQProperties rabbitMQProperties;
    private final NotificationMapper mapper;

    public void sendEvent(NotificationDeleteCalendarEventRequest request) {
        sender.send(getMessage(request))
                .doOnError(e -> log.error("Can't send a message"))
                .subscribe();
    }

    private Mono<OutboundMessage> getMessage(NotificationDeleteCalendarEventRequest request) {
        return Mono.just(new OutboundMessage(
                "",
                rabbitMQProperties.getDeleteCalendarEventQueueName(), mapper.toByteArray(request)
        ));
    }
}