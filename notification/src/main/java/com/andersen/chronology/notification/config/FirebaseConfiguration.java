package com.andersen.chronology.notification.config;

import com.andersen.chronology.notification.properties.FirebaseProperties;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@AllArgsConstructor
public class FirebaseConfiguration {

    private final FirebaseProperties firebaseProperties;

    @Bean
    public GoogleCredentials googleCredentials() {
        try (InputStream is = new FileInputStream(
                firebaseProperties.getPath() + "/firebase-service-account.json")) {
            return GoogleCredentials.fromStream(is);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Bean
    public FirebaseApp firebaseApp(GoogleCredentials credentials) {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}
