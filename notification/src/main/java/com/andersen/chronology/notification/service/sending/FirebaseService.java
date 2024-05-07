package com.andersen.chronology.notification.service.sending;

import com.andersen.chronology.rabbit.dto.notification.NotificationSendRequest;
import com.andersen.chronology.rabbit.dto.notification.NotificationSendResponse;

public interface FirebaseService {

    NotificationSendResponse send(NotificationSendRequest notification);
}
