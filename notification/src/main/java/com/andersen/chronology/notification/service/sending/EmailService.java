package com.andersen.chronology.notification.service.sending;

import com.andersen.chronology.notification.dto.NotificationSendRequest;
import com.andersen.chronology.notification.dto.NotificationSendResponse;

public interface EmailService {

    NotificationSendResponse send(NotificationSendRequest notification);
}
