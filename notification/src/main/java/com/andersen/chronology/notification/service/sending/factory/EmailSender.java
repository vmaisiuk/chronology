package com.andersen.chronology.notification.service.sending.factory;

import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendResponse;
import com.andersen.chronology.rabbit.dto.notification.NotificationType;
import com.andersen.chronology.notification.service.sending.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSender implements NotificationSender {

    private final EmailService emailService;

    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }

    @Override
    public NotificationSendResponse send(NotificationSendRequest notification) {
        return emailService.send(notification);
    }
}
