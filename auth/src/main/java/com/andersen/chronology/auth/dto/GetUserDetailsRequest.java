package com.andersen.chronology.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetUserDetailsRequest {
    @NotBlank
    private String username;
}
