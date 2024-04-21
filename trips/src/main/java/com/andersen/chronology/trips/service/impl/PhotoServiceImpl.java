package com.andersen.chronology.trips.service.impl;

import com.andersen.chronology.trips.dto.UnsplashResponse;
import com.andersen.chronology.trips.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${com.unsplash.url}")
    private String endpoint;
    @Value("${com.unsplash.accessKey}")
    private String accessKey;

    @Override
    public UnsplashResponse searchPhotos(String query) {
        return webClient
                .method(HttpMethod.GET)
                .uri(endpoint + "?query=" + query)
                .header(HttpHeaders.AUTHORIZATION, "Client-ID " + accessKey)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(UnsplashResponse.class)
                .block();
    }
}
