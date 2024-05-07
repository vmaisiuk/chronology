package com.andersen.chronology.trips.facade.impl;

import com.andersen.chronology.trips.domain.TripEntity;
import com.andersen.chronology.trips.dto.*;
import com.andersen.chronology.trips.dto.events.SendDeleteNotificationEvent;
import com.andersen.chronology.trips.dto.events.SendNewNotificationEvent;
import com.andersen.chronology.trips.dto.events.SendUpdateNotificationEvent;
import com.andersen.chronology.trips.facade.TripFacade;
import com.andersen.chronology.trips.mapper.TripMapper;
import com.andersen.chronology.trips.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripFacadeImpl implements TripFacade {

    private final TripService tripService;
    private final TripMapper tripMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public CreateTripResponse createTrip(CreateTripRequest request) {
        TripEntity trip = tripService.createTrip(tripMapper.toTripEntity(request));
        sendCreateNotification(trip.getId());
        return tripMapper.toCreateTripResponse(trip);
    }

    @Override
    public GetTripResponse getTripById(Long id) {
        return tripMapper.toGetTripResponse(tripService.getTripById(id));
    }

    @Override
    public void deleteTripById(Long id) {
        if (getTripById(id) != null) {
            tripService.deleteTripById(id);
            sendDeleteNotification(id);
        }
    }

    @Override
    public PatchTripResponse patchTripById(Long id, PatchTripRequest request) {
        TripEntity tripFromDb = tripService.getTripById(id);
        if (tripFromDb != null) {
            TripEntity trip = tripService.patchTrip(
                    tripFromDb,
                    request.getDate(),
                    request.getTripCompany()
            );
            sendUpdateNotification(trip.getId());
            return tripMapper.toPatchTripResponse(trip);
        }
        return null;
    }

    @Override
    public List<GetTripResponse> listTrip(LocalDate startDate, LocalDate endDate, String country, String companionName) {
        return tripService.listTrip(startDate, endDate, country, companionName, "slavamoisuk@gmail.com").stream()
                .map(tripMapper::toGetTripResponse)
                .toList();
    }

    @Override
    public List<GetTripResponse> getAllTrips() {
        return tripService.getAllTrips("slavamoisuk@gmail.com").stream()
                .map(tripMapper::toGetTripResponse)
                .toList();
    }

    private void sendCreateNotification(Long id) {
        SendNewNotificationEvent customSpringEvent = new SendNewNotificationEvent(this, id);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

    private void sendUpdateNotification(Long id) {
        SendUpdateNotificationEvent customSpringEvent = new SendUpdateNotificationEvent(this, id);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

    private void sendDeleteNotification(Long id) {
        SendDeleteNotificationEvent customSpringEvent = new SendDeleteNotificationEvent(this, id);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

}
