package com.andersen.chronology.notification.service.sending.factory;

import com.andersen.chronology.notification.service.sending.CalendarService;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendResponse;
import com.andersen.chronology.rabbit.dto.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarSender implements NotificationSender {

    private final CalendarService calendarService;

    @Override
    public NotificationType getType() {
        return NotificationType.CALENDAR;
    }

    @Override
    public NotificationSendResponse send(NotificationSendRequest notification) {
        return calendarService.send(notification);
    }
}
