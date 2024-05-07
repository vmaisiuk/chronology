package com.andersen.chronology.notification.service;

import com.andersen.chronology.notification.dto.NotificationSendRequest;

public interface NotificationService {

    void sendNotifications(NotificationSendRequest request);
}
