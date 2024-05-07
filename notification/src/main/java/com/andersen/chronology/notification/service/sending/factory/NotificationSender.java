package com.andersen.chronology.notification.service.sending.factory;

import com.andersen.chronology.notification.dto.NotificationSendRequest;
import com.andersen.chronology.notification.dto.NotificationSendResponse;
import com.andersen.chronology.notification.dto.NotificationType;

public interface NotificationSender {

    NotificationType getType();

    NotificationSendResponse send(NotificationSendRequest notification);

}
