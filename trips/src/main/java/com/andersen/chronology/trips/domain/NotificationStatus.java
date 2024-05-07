package com.andersen.chronology.trips.domain;

import java.util.List;

public enum NotificationStatus {
    NEW, SENT, FAILED;

    public static List<NotificationStatus> getFinalStatuses() {
        return List.of(SENT, FAILED);
    }
}
