package com.andersen.chronology.trips.facade;

import com.andersen.chronology.trips.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface TripFacade {

    CreateTripResponse createTrip(CreateTripRequest request);

    GetTripResponse getTripById(Long id);

    void deleteTripById(Long id);

    PatchTripResponse patchTripById(Long id, PatchTripRequest request);

    List<GetTripResponse> listTrip(LocalDate startDate, LocalDate endDate, String country, String companionName);

    List<GetTripResponse> getAllTrips();
}
