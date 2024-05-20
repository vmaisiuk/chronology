package com.andersen.chronology.exception.commons;

import com.andersen.chronology.exception.utils.ExceptionCode;
import org.springframework.http.HttpStatus;

public class SecurityException extends ServerException {

    public SecurityException(String message) {
        super(HttpStatus.FORBIDDEN, ExceptionCode.SECURITY_ERROR, message);
    }

    public SecurityException(String code, String message) {
        super(HttpStatus.FORBIDDEN, code, message);
    }
}
