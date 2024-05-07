package com.andersen.chronology.trips.service;

import com.andersen.chronology.rabbit.dto.notification.NotificationSendResponse;
import com.andersen.chronology.trips.domain.NotificationEntity;
import com.andersen.chronology.trips.domain.TripEntity;

import java.util.List;

public interface NotificationService {

    List<NotificationEntity> createNotifications(TripEntity trip);

    void updateNotificationStatus(NotificationSendResponse response);
}
