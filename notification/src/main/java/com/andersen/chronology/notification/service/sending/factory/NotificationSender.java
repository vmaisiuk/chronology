package com.andersen.chronology.notification.service.sending.factory;

import com.andersen.chronology.rabbit.dto.notification.NotificationChannel;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendResponse;

public interface NotificationSender {

    NotificationChannel getType();

    NotificationSendResponse send(NotificationSendRequest notification);

}
