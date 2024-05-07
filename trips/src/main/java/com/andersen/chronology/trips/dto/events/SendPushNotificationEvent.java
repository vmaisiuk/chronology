package com.andersen.chronology.trips.dto.events;

import org.springframework.context.ApplicationEvent;

public class SendPushNotificationEvent extends ApplicationEvent {

    public SendPushNotificationEvent(Object source) {
        super(source);
    }

}
