package com.andersen.chronology.security.mapper;

import com.andersen.chronology.security.dtos.AccountDetailsResponse;
import com.andersen.chronology.security.entities.AccountDetails;
import org.mapstruct.Mapper;

@Mapper
public interface AccountDetailsMapper {

    AccountDetails toAccountDetails(AccountDetailsResponse accountDetailsResponse);
}
