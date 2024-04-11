package com.andersen.chronology.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRegistrationRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
}
