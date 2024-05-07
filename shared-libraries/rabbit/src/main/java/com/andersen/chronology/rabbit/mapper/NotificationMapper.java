package com.andersen.chronology.rabbit.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Delivery;
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
    public <T> T toTargetType(Delivery delivery, Class<T> targetType) {
        return objectMapper.readValue(delivery.getBody(), targetType);
    }

    @SneakyThrows
    public byte[] toByteArray(Object request) {
        return objectMapper.writeValueAsBytes(request);
    }
}
