package com.andersen.chronology.notification.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.andersen.chronology.notification")
public class RabbitMQProperties {

    private String notificationQueueName;
    private String deleteCalendarEventQueueName;
    private String notificationStatusQueueName;
}
