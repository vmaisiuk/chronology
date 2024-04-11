package com.andersen.chronology.auth.controller;

import com.andersen.chronology.auth.facade.UserFacade;
import com.andersen.chronology.auth.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GoogleController {

    private final UserFacade userFacade;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<String> loginSuccess(OAuth2LoginAuthenticationToken authenticationToken) {


        return ResponseEntity.ok("ok");
    }

    @GetMapping("/")
    public ResponseEntity<String> loginSuccess2(OAuth2LoginAuthenticationToken authenticationToken) {


        return ResponseEntity.ok("ok");
    }
}
