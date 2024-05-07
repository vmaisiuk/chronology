package com.andersen.chronology.notification.service.impl;

import com.andersen.chronology.notification.dto.NotificationSendRequest;
import com.andersen.chronology.notification.producer.RabbitMQProducer;
import com.andersen.chronology.notification.service.NotificationService;
import com.andersen.chronology.notification.service.sending.factory.NotificationSender;
import com.andersen.chronology.notification.service.sending.factory.SendingFactory;
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

        NotificationSender sender = sendingFactory.getSender(request.getType());
        producer.sendEvent(sender.send(request));
    }
}
