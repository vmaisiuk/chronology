package com.andersen.chronology.notification.config;

import com.andersen.chronology.exception.commons.InternalException;
import com.andersen.chronology.notification.properties.GoogleCalendarProperties;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.CalendarScopes;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class GoogleCalendarConfiguration {

    public static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    public static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR);
    private final GoogleCalendarProperties googleCalendarProperties;

    @Bean
    public GoogleCredential createGoogleCredential() {
        try (InputStream is = new FileInputStream(
                googleCalendarProperties.getPath() + "/calendar-service-account.json")) {
            return GoogleCredential.fromStream(is)
                    .createScoped(SCOPES);
        } catch (IOException ioe) {
            throw new InternalException(ioe.getMessage());
        }
    }
}
