package com.andersen.chronology.notification.service.sending.factory;

import com.andersen.chronology.notification.dto.NotificationSendRequest;
import com.andersen.chronology.notification.dto.NotificationSendResponse;
import com.andersen.chronology.notification.dto.NotificationType;
import com.andersen.chronology.notification.service.sending.CalendarService;
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
