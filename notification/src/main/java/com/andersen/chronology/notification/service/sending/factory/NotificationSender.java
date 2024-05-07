package com.andersen.chronology.notification.service.sending.factory;

import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendResponse;
import com.andersen.chronology.rabbit.dto.notification.NotificationType;

public interface NotificationSender {

    NotificationType getType();

    NotificationSendResponse send(NotificationSendRequest notification);

}
