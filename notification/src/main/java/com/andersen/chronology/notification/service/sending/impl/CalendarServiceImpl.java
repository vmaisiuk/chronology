package com.andersen.chronology.notification.service.sending.impl;

import com.andersen.chronology.notification.dto.NotificationSendRequest;
import com.andersen.chronology.notification.dto.NotificationSendResponse;
import com.andersen.chronology.notification.service.sending.CalendarService;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    @SneakyThrows
    public NotificationSendResponse send(NotificationSendRequest notification) {
        NotificationSendResponse response = new NotificationSendResponse();
        response.setNotificationId(notification.getNotificationId());
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service =
                new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                        .setApplicationName(APPLICATION_NAME)
                        .build();

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
            service.events().insert(calendarId, event).execute();
            log.info("Событие успешно добавлено в Google Календарь.");
        } catch (GoogleJsonResponseException e) {
            log.error("Ошибка при добавлении события: {}", e.getDetails().getMessage());
            response.setErrorMessage(e.getDetails().getMessage());
        }
        return response;
    }
}
