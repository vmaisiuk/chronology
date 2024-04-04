package com.andersen.chronology.auth.mapper;

import com.andersen.chronology.auth.domain.UserEntity;
import com.andersen.chronology.auth.dto.UserRegistrationRequest;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserEntity toUserEntity(UserRegistrationRequest request);
}
