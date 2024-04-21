package com.andersen.chronology.trips.facade.impl;

import com.andersen.chronology.trips.dto.UnsplashResponse;
import com.andersen.chronology.trips.facade.PhotoFacade;
import com.andersen.chronology.trips.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoFacadeImpl implements PhotoFacade {

    private final PhotoService photoService;

    @Override
    public UnsplashResponse getPhotos(String city) {
        return photoService.searchPhotos(city);
    }
}
