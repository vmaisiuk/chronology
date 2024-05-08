package com.andersen.chronology.notification.service.sending.impl;


import com.andersen.chronology.notification.service.sending.CalendarService;
import com.andersen.chronology.rabbit.dto.notification.NotificationDeleteCalendarEventRequest;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.andersen.chronology.notification.config.GoogleCalendarConfiguration.JSON_FACTORY;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private static final String APPLICATION_NAME = "Chronology";
    private final GoogleCredential credential;


    @Override
    public NotificationSendResponse send(NotificationSendRequest notification) {
        NotificationSendResponse response = new NotificationSendResponse();
        response.setNotificationId(notification.getNotificationId());

        Event event = new Event();
        event.setSummary(notification.getTitle());
        event.setDescription(notification.getContent());

        LocalDateTime startDateTime = LocalDateTime.of(notification.getDate(), LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(notification.getDate(), LocalTime.MAX);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String start = startDateTime.format(formatter);
        String end = endDateTime.format(formatter);

        EventDateTime startEventDateTime = new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(start));
        EventDateTime endEventDateTime = new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(end));

        event.setStart(startEventDateTime);
        event.setEnd(endEventDateTime);

        event.setAttendees(List.of(new EventAttendee().setEmail(notification.getSendTo())));

        String calendarId = "primary";
        try {
            Calendar service = createCalendar();
            Event execute = service.events().insert(calendarId, event).execute();
            response.setCalendarEventId(execute.getId());
            log.info("Событие успешно добавлено в Google Календарь.");
        } catch (IOException | GeneralSecurityException e) {
            log.error("Ошибка при добавлении события: {}", e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public void deleteEvent(NotificationDeleteCalendarEventRequest notification) {
        String calendarId = "primary";

        try {
            Calendar service = createCalendar();
            service.events().delete(calendarId, notification.getCalendarEventId());
            log.info("Событие успешно удалено в Google Календарь.");
        } catch (IOException | GeneralSecurityException e) {
            log.error("Ошибка при удалении события: {}", e.getMessage());
        }
    }

    private Calendar createCalendar() throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
