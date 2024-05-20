package com.andersen.chronology.exception.handlers;

import com.andersen.chronology.exception.commons.ServerException;
import com.andersen.chronology.exception.dtos.ExceptionResponse;
import com.andersen.chronology.exception.utils.Constants;
import com.andersen.chronology.exception.utils.ExceptionCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Object> handleServerException(ServerException exception,
                                                        WebRequest request) {
        return handleException(exception, exception.getHttpStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleException(exception, status, request);
    }

    private ResponseEntity<Object> handleException(Exception exception,
                                                   HttpStatusCode status,
                                                   WebRequest request) {
        ExceptionResponse response = getExceptionResponse(exception, status, request);
        return handleExceptionInternal(exception, response, new HttpHeaders(), status, request);
    }

    private ExceptionResponse getExceptionResponse(
            Exception exception,
            HttpStatusCode status,
            WebRequest request) {
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .code(getExceptionCode(exception))
                .path(getPath(request))
                .build();
    }

    private String getExceptionCode(Exception exception) {
        if (exception instanceof BindException) {
            return ExceptionCode.VALIDATION_ERROR;
        }
        if (exception instanceof ServerException serverException) {
            return serverException.getCode();
        }
        return null;
    }

    private String getPath(WebRequest request) {
        String path = request.getDescription(false);
        return path.startsWith(Constants.URI_PREFIX) ?
                path.substring(Constants.URI_PREFIX.length()) :
                null;
    }
}
