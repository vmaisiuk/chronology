package com.andersen.chronology.notification.service;


import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;

public interface NotificationService {

    void sendNotifications(NotificationSendRequest request);
}
