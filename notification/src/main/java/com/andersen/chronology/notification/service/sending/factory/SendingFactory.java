package com.andersen.chronology.notification.service.sending.factory;

import com.andersen.chronology.rabbit.dto.notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SendingFactory {

    private final Map<NotificationType, NotificationSender> senderMap;

    @Autowired
    private SendingFactory(List<NotificationSender> notificationSenders) {
        senderMap = notificationSenders.stream().collect(Collectors.toUnmodifiableMap(NotificationSender::getType, Function.identity()));
    }

    public NotificationSender getSender(NotificationType notificationType) {
        return Optional.ofNullable(senderMap.get(notificationType)).orElseThrow(IllegalArgumentException::new);
    }
}
