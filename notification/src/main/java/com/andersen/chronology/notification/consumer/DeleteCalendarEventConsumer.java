package com.andersen.chronology.notification.consumer;

import com.andersen.chronology.notification.properties.RabbitMQProperties;
import com.andersen.chronology.notification.service.sending.CalendarService;
import com.andersen.chronology.rabbit.dto.notification.NotificationDeleteCalendarEventRequest;
import com.andersen.chronology.rabbit.mapper.NotificationMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.rabbitmq.Receiver;

@Service
@Slf4j
@AllArgsConstructor
public class DeleteCalendarEventConsumer {

    private final CalendarService calendarService;
    private final RabbitMQProperties rabbitMQProperties;
    private final Receiver receiver;
    private final NotificationMapper notificationMapper;

    @PostConstruct
    public void consume() {
        receiver
                .consumeAutoAck(rabbitMQProperties.getDeleteCalendarEventQueueName())
                .map(message -> notificationMapper.toTargetType(message, NotificationDeleteCalendarEventRequest.class))
                .doOnNext(calendarService::deleteEvent)
                .subscribe(
                        notification -> log
                                .debug("The process of deleting calendar event is over."),
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
