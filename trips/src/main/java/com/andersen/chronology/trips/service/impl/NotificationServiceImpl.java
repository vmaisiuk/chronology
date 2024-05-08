package com.andersen.chronology.trips.service.impl;

import com.andersen.chronology.rabbit.dto.notification.NotificationChannel;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendResponse;
import com.andersen.chronology.trips.domain.NotificationEntity;
import com.andersen.chronology.trips.domain.NotificationStatus;
import com.andersen.chronology.trips.domain.NotificationType;
import com.andersen.chronology.trips.domain.TripEntity;
import com.andersen.chronology.trips.dto.events.SendPushNotificationEvent;
import com.andersen.chronology.trips.repository.NotificationRepository;
import com.andersen.chronology.trips.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public List<NotificationEntity> createNotifications(TripEntity trip) {
        return notificationRepository.saveAll(toNotifications(trip));
    }

    @Override
    @Transactional
    public void updateNotificationStatus(NotificationSendResponse response) {
        notificationRepository.findById(response.getNotificationId()).ifPresent(notification -> {
            if (StringUtils.isNotBlank(response.getErrorMessage())) {
                notification.setStatus(NotificationStatus.FAILED);
                notification.setErrorMessage(response.getErrorMessage());
            } else {
                notification.setStatus(NotificationStatus.SENT);
            }
            notification.setCalendarEventId(response.getCalendarEventId());
            notificationRepository.save(notification);
            checkNotificationsStatus(notification.getTripId());
        });
    }

    @Override
    public NotificationEntity createPushNotification() {
        return notificationRepository.save(createPushNotificationEntity());
    }

    @Override
    public List<NotificationEntity> createDeleteNotification(TripEntity trip) {
        return notificationRepository.saveAll(deleteNotification(trip));
    }

    @Override
    public Optional<NotificationEntity> checkCalendarEvent(Long tripId) {
        return notificationRepository.findFirstByTripIdAndCalendarEventIdNotNull(tripId);
    }

    private void checkNotificationsStatus(Long tripId) {
        boolean isAllInFinalStatus = notificationRepository.findAllByTripId(tripId).stream()
                .allMatch(notification -> NotificationStatus.getFinalStatuses().contains(notification.getStatus()));

        if (isAllInFinalStatus) {
            SendPushNotificationEvent customSpringEvent = new SendPushNotificationEvent(this);
            applicationEventPublisher.publishEvent(customSpringEvent);
        }
    }

    private List<NotificationEntity> toNotifications(TripEntity trip) {
        Set<String> tripCompany = trip.getTripCompany();

        if (!CollectionUtils.isEmpty(tripCompany)) {
            return tripCompany.stream()
                    .map(companion -> {
                        NotificationEntity entity = new NotificationEntity();

                        entity.setTripId(trip.getId());

                        if (companion.contains("@gmail.com")) {
                            entity.setChannels(Set.of(NotificationChannel.CALENDAR, NotificationChannel.EMAIL));
                        } else {
                            entity.setChannels(Set.of(NotificationChannel.EMAIL));
                        }

                        entity.setSendTo(companion);
                        entity.setTitle(String.format("%s %s: %s", trip.getCountry(), trip.getCity(), trip.getDate()));
                        entity.setContent(String.format("%s %s", "slavamoisuk@gmail.com", "invites you on a trip."));
                        entity.setDate(trip.getDate());
                        entity.setStatus(NotificationStatus.NEW);
                        entity.setType(NotificationType.SEND_NEW);

                        return entity;
                    })
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }

    private NotificationEntity createPushNotificationEntity() {
        NotificationEntity entity = new NotificationEntity();

        entity.setChannels(Set.of(NotificationChannel.PUSH));
        entity.setSendTo("slavamoisuk@gmail.com");
        entity.setTitle("All invites sent to your companions.");
        entity.setContent("All invites sent to your companions.");
        entity.setStatus(NotificationStatus.NEW);
        entity.setType(NotificationType.ALL_SET);

        return entity;
    }

    private List<NotificationEntity> deleteNotification(TripEntity trip) {
        Set<String> tripCompany = trip.getTripCompany();

        if (!CollectionUtils.isEmpty(tripCompany)) {
            return tripCompany.stream()
                    .map(companion -> {
                        NotificationEntity entity = new NotificationEntity();

                        entity.setTripId(trip.getId());
                        entity.setChannels(Set.of(NotificationChannel.EMAIL));
                        entity.setSendTo(companion);
                        entity.setTitle(String.format("%s %s: %s", trip.getCountry(), trip.getCity(), trip.getDate()));
                        entity.setContent(String.format("%s %s", "slavamoisuk@gmail.com", "canceled this trip."));
                        entity.setDate(trip.getDate());
                        entity.setStatus(NotificationStatus.NEW);
                        entity.setType(NotificationType.SEND_NEW);

                        return entity;
                    })
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }
}
