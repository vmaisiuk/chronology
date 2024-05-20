package com.andersen.chronology.security.service.client;

import com.andersen.chronology.security.dtos.AccountDetailsResponse;

public interface UserServiceClient {

    AccountDetailsResponse getAccountDetailsByUsername(String username);
}
