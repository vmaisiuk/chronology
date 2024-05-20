package com.andersen.chronology.security.service;

import com.andersen.chronology.security.dtos.AccountDetailsResponse;
import com.andersen.chronology.security.entities.AccountDetails;
import com.andersen.chronology.security.mapper.AccountDetailsMapper;
import com.andersen.chronology.security.service.client.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountDetailsServiceImpl implements UserDetailsService {

    private final UserServiceClient userServiceClient;
    private final AccountDetailsMapper accountDetailsMapper;

    @Override
    public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDetails accountDetails;
        try {
            accountDetails = getAccountDetailsFromClient(username);
        } catch (Exception cacheException) {
            throw getUsernameNotFoundException();
        }
        return accountDetails;
    }

    private AccountDetails getAccountDetailsFromClient(String username) {
        AccountDetailsResponse response = userServiceClient.getAccountDetailsByUsername(username);
        return Optional.ofNullable(response)
                .map(accountDetailsMapper::toAccountDetails)
                .orElseThrow(this::getUsernameNotFoundException);
    }

    private UsernameNotFoundException getUsernameNotFoundException() {
        return new UsernameNotFoundException("Authentication failed. Username not found.");
    }
}
