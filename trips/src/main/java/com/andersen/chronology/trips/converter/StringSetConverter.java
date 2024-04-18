package com.andersen.chronology.trips.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String> {

    private static final CharSequence SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(Set<String> stringSet) {
        return !CollectionUtils.isEmpty(stringSet) ?
                stringSet.stream().map(Object::toString).collect(Collectors.joining(SPLIT_CHAR)) : null;
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        return dbData != null ? Stream.of(dbData.trim().split("\\s*,\\s*"))
                .collect(Collectors.toSet()) : null;
    }
}
