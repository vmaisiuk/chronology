package com.andersen.chronology.trips.service.impl;

import com.andersen.chronology.trips.domain.NotificationEntity;
import com.andersen.chronology.trips.dto.events.SendNewNotificationEvent;
import com.andersen.chronology.trips.mapper.NotificationMapper;
import com.andersen.chronology.trips.producer.SendNewNotificationProducer;
import com.andersen.chronology.trips.service.EventService;
import com.andersen.chronology.trips.service.NotificationService;
import com.andersen.chronology.trips.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final TripService tripService;
    private final NotificationService notificationService;
    private final SendNewNotificationProducer sendNewNotificationProducer;
    private final NotificationMapper notificationMapper;

    @Async
    @Override
    @EventListener(SendNewNotificationEvent.class)
    public void handleNewNotificationEvent(Long id) {
        List<NotificationEntity> notifications = notificationService.createNotifications(tripService.getTripById(id));
        if (!CollectionUtils.isEmpty(notifications)) {
            notifications.forEach(notification -> sendNewNotificationProducer.sendEvent(notificationMapper.toNotificationSendRequest(notification)));
        }
    }
}
