package com.andersen.chronology.trips.controller;

import com.andersen.chronology.trips.dto.UnsplashResponse;
import com.andersen.chronology.trips.facade.PhotoFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoFacade photoFacade;

    @GetMapping
    public UnsplashResponse allPhotos(String city) {
        return photoFacade.getPhotos(city);
    }
}
