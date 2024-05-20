package com.andersen.chronology.auth.facade.impl;

import com.andersen.chronology.auth.dto.UserRegistrationRequest;
import com.andersen.chronology.auth.facade.UserFacade;
import com.andersen.chronology.auth.mapper.UserMapper;
import com.andersen.chronology.auth.service.UserService;
import com.andersen.chronology.security.dtos.AccountDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserDetailsService mongoAuthUserDetailService;

    @Override
    public UserDetails registerUser(UserRegistrationRequest request) {
        return userService.registerUser(userMapper.toUserEntity(request));
    }

    @Override
    public UserDetails getUserByName(String username) {
        return mongoAuthUserDetailService.loadUserByUsername(username);
    }

    @Override
    public AccountDetailsResponse getUserDetails(String username) {
        return userMapper.toAccountDetailsResponse(userService.getUser(username));
    }
}
