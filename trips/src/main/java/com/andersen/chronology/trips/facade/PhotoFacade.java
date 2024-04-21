package com.andersen.chronology.trips.facade;

import com.andersen.chronology.trips.dto.UnsplashResponse;

public interface PhotoFacade {

    UnsplashResponse getPhotos(String city);
}
