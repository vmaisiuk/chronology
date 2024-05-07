package com.andersen.chronology.trips.converter;

import com.andersen.chronology.rabbit.dto.notification.NotificationType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Converter
public class NotificationTypeSetConverter implements AttributeConverter<Set<NotificationType>, String> {

    private static final CharSequence SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(Set<NotificationType> stringSet) {
        return !CollectionUtils.isEmpty(stringSet) ?
                stringSet.stream().map(Object::toString).collect(Collectors.joining(SPLIT_CHAR)) : null;
    }

    @Override
    public Set<NotificationType> convertToEntityAttribute(String dbData) {
        return dbData != null ? Stream.of(dbData.trim().split("\\s*,\\s*"))
                .map(NotificationType::valueOf)
                .collect(Collectors.toSet()) : null;
    }
}
