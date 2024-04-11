package com.andersen.chronology.auth.facade;

import com.andersen.chronology.auth.dto.UserRegistrationRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserFacade {

    UserDetails registerUser(UserRegistrationRequest request);
}
