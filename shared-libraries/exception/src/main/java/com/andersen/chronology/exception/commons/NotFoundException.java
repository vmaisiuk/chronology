package com.andersen.chronology.exception.commons;

import com.andersen.chronology.exception.utils.ExceptionCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ServerException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, ExceptionCode.NOT_FOUND_ERROR, message);
    }

    public NotFoundException(String code, String message) {
        super(HttpStatus.NOT_FOUND, code, message);
    }
}
