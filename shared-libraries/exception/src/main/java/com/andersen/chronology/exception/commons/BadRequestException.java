package com.andersen.chronology.exception.commons;

import com.andersen.chronology.exception.utils.ExceptionCode;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ServerException {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, ExceptionCode.BAD_REQUEST_ERROR, message);
    }

    public BadRequestException(String code, String message) {
        super(HttpStatus.BAD_REQUEST, code, message);
    }
}
