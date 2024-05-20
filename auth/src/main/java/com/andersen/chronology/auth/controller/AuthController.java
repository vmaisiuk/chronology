package com.andersen.chronology.auth.controller;

import com.andersen.chronology.auth.dto.GetUserDetailsRequest;
import com.andersen.chronology.auth.facade.UserFacade;
import com.andersen.chronology.security.dtos.AccountDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/chronology")
@RequiredArgsConstructor
public class AuthController {

    private final UserFacade userFacade;

    @PostMapping()
    public AccountDetailsResponse getUserDetails(@RequestBody GetUserDetailsRequest request) {
        return userFacade.getUserDetails(request.getUsername());
    }
}
