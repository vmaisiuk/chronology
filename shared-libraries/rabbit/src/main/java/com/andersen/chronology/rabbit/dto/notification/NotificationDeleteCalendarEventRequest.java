package com.andersen.chronology.rabbit.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDeleteCalendarEventRequest {

    private String calendarEventId;
}
