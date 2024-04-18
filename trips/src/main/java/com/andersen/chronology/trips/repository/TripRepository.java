package com.andersen.chronology.trips.repository;

import com.andersen.chronology.trips.domain.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, Long>,
        JpaSpecificationExecutor<TripEntity> {
}
