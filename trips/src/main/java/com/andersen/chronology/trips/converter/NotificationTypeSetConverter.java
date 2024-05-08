package com.andersen.chronology.trips.converter;

import com.andersen.chronology.rabbit.dto.notification.NotificationChannel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Converter
public class NotificationTypeSetConverter implements AttributeConverter<Set<NotificationChannel>, String> {

    private static final CharSequence SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(Set<NotificationChannel> stringSet) {
        return !CollectionUtils.isEmpty(stringSet) ?
                stringSet.stream().map(Object::toString).collect(Collectors.joining(SPLIT_CHAR)) : null;
    }

    @Override
    public Set<NotificationChannel> convertToEntityAttribute(String dbData) {
        return dbData != null ? Stream.of(dbData.trim().split("\\s*,\\s*"))
                .map(NotificationChannel::valueOf)
                .collect(Collectors.toSet()) : null;
    }
}
