package com.andersen.chronology.rabbit.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Delivery;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(imports = {UUID.class, ObjectMapper.class})
public abstract class NotificationMapper {

    @SneakyThrows
    public <T> T toTargetType(Delivery delivery, Class<T> targetType) {
        return new ObjectMapper().readValue(delivery.getBody(), targetType);
    }

    @SneakyThrows
    public byte[] toByteArray(Object request) {
        return new ObjectMapper().writeValueAsBytes(request);
    }
}
