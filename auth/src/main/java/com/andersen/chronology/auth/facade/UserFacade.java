package com.andersen.chronology.auth.facade;

import com.andersen.chronology.auth.dto.UserRegistrationRequest;
import com.andersen.chronology.security.dtos.AccountDetailsResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserFacade {

    UserDetails registerUser(UserRegistrationRequest request);

    UserDetails getUserByName(String username);

    AccountDetailsResponse getUserDetails(String username);
}
