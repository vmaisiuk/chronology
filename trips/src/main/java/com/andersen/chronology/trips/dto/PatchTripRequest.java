package com.andersen.chronology.trips.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PatchTripRequest {

    private LocalDate date;
    private Set<String> tripCompany;
}
