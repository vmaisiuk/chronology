package com.andersen.chronology.exception.commons;


import com.andersen.chronology.exception.utils.ExceptionCode;
import org.springframework.http.HttpStatus;

public class InternalException extends ServerException {

    public InternalException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionCode.INTERNAL_ERROR, message);
    }

    public InternalException(String code, String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, code, message);
    }
}
