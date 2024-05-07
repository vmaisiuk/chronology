package com.andersen.chronology.notification.service.sending.impl;

import com.andersen.chronology.notification.service.sending.FirebaseService;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirebaseServiceImpl implements FirebaseService {

    private final FirebaseMessaging firebaseMessaging;

    @Override
    public NotificationSendResponse send(NotificationSendRequest notification) {
        NotificationSendResponse response = new NotificationSendResponse();
        response.setNotificationId(notification.getNotificationId());
        try {
            String notificationId = firebaseMessaging.send(getMessage(notification));
            log.debug("Sent push notification with id {}", notificationId);
            return response;
        } catch (FirebaseMessagingException exception) {
            log.error(exception.getMessage());
            response.setErrorMessage(exception.getMessage());
            return response;
        }
    }

    private Message getMessage(NotificationSendRequest request) {
        Message.Builder builder = Message.builder()
                .setToken(request.getSendTo())
                .setNotification(
                        Notification.builder()
                                .setTitle(request.getTitle())
                                .setBody(request.getContent())
                                .build()
                );
        return builder.build();
    }
}
