package com.andersen.chronology.trips.service.impl;

import com.andersen.chronology.trips.domain.TripEntity;
import com.andersen.chronology.trips.repository.TripRepository;
import com.andersen.chronology.trips.repository.specification.TripSpecification;
import com.andersen.chronology.trips.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    @Override
    @Transactional
    public TripEntity createTrip(TripEntity tripEntity) {
        return tripRepository.save(tripEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public TripEntity getTripById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteTripById(Long id) {
        tripRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TripEntity patchTrip(TripEntity tripFromDb, LocalDate date, Set<String> tripCompany) {
        tripFromDb.setDate(date);
        tripFromDb.setTripCompany(tripCompany);
        return tripRepository.save(tripFromDb);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TripEntity> listTrip(LocalDate startDate, LocalDate endDate, String country, String companionName, String userName) {
        Specification<TripEntity> spec = TripSpecification.getAllTrips(startDate, endDate, country, companionName, userName);
        return tripRepository.findAll(spec);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TripEntity> getAllTrips(String userName) {
        Specification<TripEntity> spec = TripSpecification.getAllTrips(userName);
        return tripRepository.findAll(spec);
    }
}
