package com.andersen.chronology.notification.service.impl;

import com.andersen.chronology.notification.producer.RabbitMQProducer;
import com.andersen.chronology.notification.service.NotificationService;
import com.andersen.chronology.notification.service.sending.factory.NotificationSender;
import com.andersen.chronology.notification.service.sending.factory.SendingFactory;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final SendingFactory sendingFactory;
    private final RabbitMQProducer producer;

    @Override
    public void sendNotifications(NotificationSendRequest request) {
        log.debug("The process of sending notification {} has started.", request.getNotificationId());

        request.getChannels().forEach(channel -> {
            NotificationSender sender = sendingFactory.getSender(channel);
            producer.sendEvent(sender.send(request));
        });
    }
}
