package com.andersen.chronology.trips.service;

import com.andersen.chronology.trips.domain.TripEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface TripService {

    TripEntity createTrip(TripEntity tripEntity);

    TripEntity getTripById(Long id);

    void deleteTripById(Long id);

    TripEntity patchTrip(TripEntity tripFromDb, LocalDate date, Set<String> tripCompany);

    List<TripEntity> listTrip(LocalDate startDate, LocalDate endDate, String country, String companionName, String userName);

    List<TripEntity> getAllTrips(String userName);
}
