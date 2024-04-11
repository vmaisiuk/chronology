package com.andersen.chronology.auth.service;

import com.andersen.chronology.auth.domain.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails registerUser(UserEntity request);
}
