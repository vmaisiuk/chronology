package com.andersen.chronology.trips.repository;

import com.andersen.chronology.trips.domain.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findAllByTripId(Long tripId);

    Optional<NotificationEntity> findFirstByTripIdAndCalendarEventIdNotNull(Long tripId);
}
