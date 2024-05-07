package com.andersen.chronology.trips.domain;

import com.andersen.chronology.rabbit.dto.notification.NotificationType;
import com.andersen.chronology.trips.converter.NotificationTypeSetConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "trip_id")
    private Long tripId;
    @Column(name = "channels")
    @Convert(converter = NotificationTypeSetConverter.class)
    private Set<NotificationType> channels;
    @Column(name = "send_to")
    private String sendTo;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
    @Column(name = "error_message")
    private String errorMessage;
    @Column(name = "calendar_event_id")
    private String calendarEventId;
}
