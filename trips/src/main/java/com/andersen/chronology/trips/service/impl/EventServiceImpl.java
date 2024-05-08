package com.andersen.chronology.trips.service.impl;

import com.andersen.chronology.rabbit.dto.notification.NotificationDeleteCalendarEventRequest;
import com.andersen.chronology.trips.domain.NotificationEntity;
import com.andersen.chronology.trips.dto.events.SendDeleteNotificationEvent;
import com.andersen.chronology.trips.dto.events.SendNewNotificationEvent;
import com.andersen.chronology.trips.dto.events.SendPushNotificationEvent;
import com.andersen.chronology.trips.dto.events.SendUpdateNotificationEvent;
import com.andersen.chronology.trips.mapper.NotificationMapper;
import com.andersen.chronology.trips.producer.DeleteCalendarEventProducer;
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
    private final DeleteCalendarEventProducer deleteCalendarEventProducer;
    private final NotificationMapper notificationMapper;

    @Async
    @Override
    @EventListener(SendNewNotificationEvent.class)
    public void handleNewNotificationEvent(Long id) {
        newNotificationEvent(id);
    }

    @Async
    @Override
    @EventListener(SendDeleteNotificationEvent.class)
    public void handleDeleteNotificationEvent(Long id) {
        deleteNotificationEvent(id);
    }

    @Async
    @Override
    @EventListener(SendUpdateNotificationEvent.class)
    public void handleUpdateNotificationEvent(Long id) {
        deleteNotificationEvent(id);
        newNotificationEvent(id);
    }

    @Async
    @Override
    @EventListener(SendPushNotificationEvent.class)
    public void handlePushNotificationEvent() {
        sendNewNotificationProducer.sendEvent(notificationMapper.toNotificationSendRequest(notificationService.createPushNotification()));
    }

    private void newNotificationEvent(Long id) {
        List<NotificationEntity> notifications = notificationService.createNotifications(tripService.getTripById(id));
        if (!CollectionUtils.isEmpty(notifications)) {
            notifications.forEach(notification -> sendNewNotificationProducer.sendEvent(notificationMapper.toNotificationSendRequest(notification)));
        }
    }

    private void deleteNotificationEvent(Long id) {
        List<NotificationEntity> notifications = notificationService.createDeleteNotification(tripService.getTripById(id));
        if (!CollectionUtils.isEmpty(notifications)) {
            notifications.forEach(notification -> sendNewNotificationProducer.sendEvent(notificationMapper.toNotificationSendRequest(notification)));
        }
        notificationService.checkCalendarEvent(id).ifPresent(notification -> {
            NotificationDeleteCalendarEventRequest request = new NotificationDeleteCalendarEventRequest();
            request.setCalendarEventId(notification.getCalendarEventId());
            deleteCalendarEventProducer.sendEvent(request);
        });
    }
}
