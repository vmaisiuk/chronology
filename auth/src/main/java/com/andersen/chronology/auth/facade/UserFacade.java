package com.andersen.chronology.auth.facade;

import com.andersen.chronology.auth.dto.UserRegistrationRequest;

public interface UserFacade {

    void registerUser(UserRegistrationRequest request);
}
