package com.andersen.chronology.trips.dto.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SendUpdateNotificationEvent extends ApplicationEvent {

    private final Long id;

    public SendUpdateNotificationEvent(Object source, Long id) {
        super(source);
        this.id = id;
    }

}
