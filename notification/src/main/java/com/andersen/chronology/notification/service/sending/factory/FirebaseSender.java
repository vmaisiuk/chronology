package com.andersen.chronology.notification.service.sending.factory;

import com.andersen.chronology.notification.dto.NotificationSendRequest;
import com.andersen.chronology.notification.dto.NotificationSendResponse;
import com.andersen.chronology.notification.dto.NotificationType;
import com.andersen.chronology.notification.service.sending.FirebaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirebaseSender implements NotificationSender {

    private final FirebaseService firebaseService;

    @Override
    public NotificationType getType() {
        return NotificationType.PUSH;
    }

    @Override
    public NotificationSendResponse send(NotificationSendRequest notification) {
        return firebaseService.send(notification);
    }
}
