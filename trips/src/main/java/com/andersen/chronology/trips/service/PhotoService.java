package com.andersen.chronology.trips.service;

import com.andersen.chronology.trips.dto.UnsplashResponse;

public interface PhotoService {

    UnsplashResponse searchPhotos(String query);
}
