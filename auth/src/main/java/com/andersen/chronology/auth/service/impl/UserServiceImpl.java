package com.andersen.chronology.auth.service.impl;

import com.andersen.chronology.auth.domain.UserEntity;
import com.andersen.chronology.auth.exception.UserNotFoundException;
import com.andersen.chronology.auth.repository.UserRepository;
import com.andersen.chronology.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails registerUser(UserEntity request) {
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        request.setToken(getToken());
        return userRepository.save(request);
    }

    @Override
    public UserEntity getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with name '%s' not found.", username)));
    }

    private String getToken() {
        return "token from firebase";
    }
}
