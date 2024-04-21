package com.andersen.chronology.trips.dto;

import lombok.Data;

import java.util.List;

@Data
public class UnsplashResponse {

    private List<PhotoDto> results;
}
