package com.andersen.chronology.security.service.client.impl;

import com.andersen.chronology.security.dtos.AccountDetailsResponse;
import com.andersen.chronology.security.dtos.GetUserDetailsRequest;
import com.andersen.chronology.security.properties.AuthProperties;
import com.andersen.chronology.security.service.client.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserServiceClientImpl implements UserServiceClient {

    private final WebClient userServiceClient;
    private final AuthProperties authProperties;

    @Override
    public AccountDetailsResponse getAccountDetailsByUsername(String username) {
        GetUserDetailsRequest request = new GetUserDetailsRequest();
        request.setUsername(username);
        return userServiceClient.post()
                .uri(authProperties.getAccountDetailsPath())
                .body(Mono.just(request), GetUserDetailsRequest.class)
                .retrieve()
                .bodyToMono(AccountDetailsResponse.class)
                .block();
    }
}
