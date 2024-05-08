package com.andersen.chronology.rabbit.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSendRequest {

    private Long notificationId;
    private Set<NotificationChannel> channels;
    private String sendTo;
    private String title;
    private String content;
    private LocalDate date;
}
