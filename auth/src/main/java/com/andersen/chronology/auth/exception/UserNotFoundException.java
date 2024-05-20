package com.andersen.chronology.auth.exception;

import com.andersen.chronology.exception.commons.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
