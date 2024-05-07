package com.andersen.chronology.notification.consumer;

import com.andersen.chronology.notification.mapper.NotificationMapper;
import com.andersen.chronology.notification.properties.RabbitMQProperties;
import com.andersen.chronology.notification.service.NotificationService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.rabbitmq.Receiver;

@Service
@Slf4j
@AllArgsConstructor
public class RabbitMQConsumer {

    private final NotificationService notificationService;
    private final RabbitMQProperties rabbitMQProperties;
    private final Receiver receiver;
    private final NotificationMapper notificationMapper;

    @PostConstruct
    public void consume() {
        receiver
                .consumeAutoAck(rabbitMQProperties.getNotificationQueueName())
                .map(message -> notificationMapper.toNotificationSendRequest(message.getBody()))
                .doOnNext(notificationService::sendNotifications)
                .subscribe(
                        notification -> log
                                .debug("The process of sending notification {} is over.",
                                        notification.getNotificationId()),
                        throwable -> log
                                .error("Can't read a message from queue, and get error '{}'",
                                        throwable.getMessage())
                );
    }

    @PreDestroy
    public void close() {
        receiver.close();
    }
}
