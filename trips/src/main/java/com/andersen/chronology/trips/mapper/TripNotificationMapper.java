package com.andersen.chronology.trips.mapper;

import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;
import com.andersen.chronology.trips.domain.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TripNotificationMapper {

    @Mapping(target = "notificationId", source = "id")
    NotificationSendRequest toNotificationSendRequest(NotificationEntity notificationEntity);
}
