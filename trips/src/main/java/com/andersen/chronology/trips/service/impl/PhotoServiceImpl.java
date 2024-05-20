package com.andersen.chronology.trips.service.impl;

import com.andersen.chronology.trips.dto.UnsplashResponse;
import com.andersen.chronology.trips.properties.PhotoProperties;
import com.andersen.chronology.trips.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final WebClient webClient;
    private final PhotoProperties photoProperties;

    @Override
    public UnsplashResponse searchPhotos(String query) {
        return webClient
                .method(HttpMethod.GET)
                .uri(photoProperties.getUrl() + "?query=" + query)
                .header(HttpHeaders.AUTHORIZATION, "Client-ID " + photoProperties.getAccessKey())
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(UnsplashResponse.class)
                .block();
    }
}
