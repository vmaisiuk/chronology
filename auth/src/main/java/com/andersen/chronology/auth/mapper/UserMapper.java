package com.andersen.chronology.auth.mapper;

import com.andersen.chronology.auth.domain.Role;
import com.andersen.chronology.auth.domain.UserEntity;
import com.andersen.chronology.auth.dto.UserRegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    @Mapping(target = "roles", expression = "java(getDefaultRole())")
    UserEntity toUserEntity(UserRegistrationRequest request);

    default Set<Role> getDefaultRole() {
        return Set.of(Role.USER);
    }
}
