package com.andersen.chronology.auth.controller;

import com.andersen.chronology.auth.dto.LoginRequest;
import com.andersen.chronology.auth.dto.UserRegistrationRequest;
import com.andersen.chronology.auth.facade.UserFacade;
import com.andersen.chronology.auth.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/api/chronology")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String registerUser(@ModelAttribute UserRegistrationRequest request) {
        userFacade.registerUser(request);
        return "login";
    }

    @PostMapping("/sign-in")
    public String loginUser(@ModelAttribute LoginRequest loginRequest) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            if (userDetails == null) {
                throw new BadCredentialsException("Invalid username or password");
            }

            return "home";
        } catch (AuthenticationException exception) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
