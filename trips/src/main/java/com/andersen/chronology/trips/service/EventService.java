package com.andersen.chronology.trips.service;

public interface EventService {

    void handleNewNotificationEvent(Long id);

    void handleDeleteNotificationEvent(Long id);

    void handleUpdateNotificationEvent(Long id);

    void handlePushNotificationEvent();
}
