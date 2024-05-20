package com.andersen.chronology.trips.controller;

import com.andersen.chronology.trips.dto.*;
import com.andersen.chronology.trips.facade.TripFacade;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/trips")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TripController {

    private final TripFacade tripFacade;

    @PostMapping
    public CreateTripResponse createTrip(@RequestBody CreateTripRequest request) {
        return tripFacade.createTrip(request);
    }

    @GetMapping
    public List<GetTripResponse> allTrips() {
        return tripFacade.getAllTrips();
    }

    @GetMapping("/{id}")
    public GetTripResponse getTripById(@PathVariable Long id) {
        return tripFacade.getTripById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTripById(@PathVariable Long id) {
        tripFacade.deleteTripById(id);
    }

    @PatchMapping("/{id}")
    public PatchTripResponse patchTripById(@PathVariable Long id, @RequestBody PatchTripRequest request) {
        return tripFacade.patchTripById(id, request);
    }

    @PostMapping("/list")
    public List<GetTripResponse> listTrip(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String companionName) {
        return tripFacade.listTrip(startDate, endDate, country, companionName);
    }
}
