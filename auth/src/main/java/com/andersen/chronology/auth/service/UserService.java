package com.andersen.chronology.auth.service;

import com.andersen.chronology.auth.domain.UserEntity;

public interface UserService {

    void registerUser(UserEntity request);
}
