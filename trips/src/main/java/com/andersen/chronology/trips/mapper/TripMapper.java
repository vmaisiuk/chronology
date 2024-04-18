package com.andersen.chronology.trips.mapper;

import com.andersen.chronology.trips.domain.TripEntity;
import com.andersen.chronology.trips.dto.CreateTripRequest;
import com.andersen.chronology.trips.dto.CreateTripResponse;
import com.andersen.chronology.trips.dto.GetTripResponse;
import com.andersen.chronology.trips.dto.PatchTripResponse;
import org.mapstruct.Mapper;

@Mapper
public interface TripMapper {

    TripEntity toTripEntity(CreateTripRequest request);

    CreateTripResponse toCreateTripResponse(TripEntity tripEntity);

    GetTripResponse toGetTripResponse(TripEntity tripEntity);

    PatchTripResponse toPatchTripResponse(TripEntity tripEntity);
}
