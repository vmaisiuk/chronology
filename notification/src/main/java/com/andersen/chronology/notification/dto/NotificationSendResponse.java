package com.andersen.chronology.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSendResponse {

    private Long notificationId;
    private String errorMessage;
}
