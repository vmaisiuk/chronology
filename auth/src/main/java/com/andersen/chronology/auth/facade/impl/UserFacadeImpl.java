package com.andersen.chronology.auth.facade.impl;

import com.andersen.chronology.auth.dto.UserRegistrationRequest;
import com.andersen.chronology.auth.facade.UserFacade;
import com.andersen.chronology.auth.mapper.UserMapper;
import com.andersen.chronology.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDetails registerUser(UserRegistrationRequest request) {
        return userService.registerUser(userMapper.toUserEntity(request));
    }
}
