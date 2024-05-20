package com.andersen.chronology.security.dtos;

import com.andersen.chronology.security.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsResponse {

    private String username;
    private String name;
    private boolean active;
    private Set<Role> roles;
    private String token;
}
