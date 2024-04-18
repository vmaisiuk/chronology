package com.andersen.chronology.trips.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CreateTripResponse {

    private Long id;
    private String city;
    private String country;
    private LocalDate date;
    private Set<String> tripCompany;
    private String userName;
}
