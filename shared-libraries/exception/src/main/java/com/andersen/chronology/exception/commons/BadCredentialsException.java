package com.andersen.chronology.exception.commons;

import com.andersen.chronology.exception.utils.ExceptionCode;
import org.springframework.http.HttpStatus;

public class BadCredentialsException extends ServerException {

    public BadCredentialsException(String message) {
        super(HttpStatus.NOT_ACCEPTABLE, ExceptionCode.BAD_CREDENTIALS_ERROR, message);
    }

    public BadCredentialsException(String code, String message) {
        super(HttpStatus.NOT_ACCEPTABLE, code, message);
    }
}
