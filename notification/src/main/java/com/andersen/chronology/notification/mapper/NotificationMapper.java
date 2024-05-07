package com.andersen.chronology.notification.mapper;

import com.andersen.chronology.notification.dto.NotificationSendRequest;
import com.andersen.chronology.notification.dto.NotificationSendResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(imports = {UUID.class, ObjectMapper.class})
@Component
@RequiredArgsConstructor
public abstract class NotificationMapper {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public NotificationSendRequest toNotificationSendRequest(byte[] request) {
        return objectMapper.readValue(request, NotificationSendRequest.class);
    }

    @SneakyThrows
    public byte[] toByteArray(NotificationSendResponse request) {
        return objectMapper.writeValueAsBytes(request);
    }
}
