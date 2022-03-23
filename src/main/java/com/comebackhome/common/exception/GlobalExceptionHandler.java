package com.comebackhome.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static com.comebackhome.common.exception.ErrorCode.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String LOG_FORMAT = "Class : {}, Message : {}";
    private static final String LOG_CODE_FORMAT = "Class : {}, Code : {}, Message : {}";
    private static final String LOG_FIELD_FORMAT = "Class : {}, field : {} ,Message : {}";
    private static final String LOG_CODE_FIELD_FORMAT = "Class : {}, Code : {}, field : {} ,Message : {}";

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> bindException(BindException e) {

        final String code = BINDING_EXCEPTION.getCode();
        final String message = BINDING_EXCEPTION.getMessage();

        log.warn(LOG_CODE_FORMAT, e.getClass().getSimpleName(), code, message);

        ErrorResponse response = ErrorResponse.of(message, code, e.getBindingResult());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity handleMissingParams(MissingRequestHeaderException e) {

        final String code = MISSING_REQUEST_HEADER.getCode();
        final String message = MISSING_REQUEST_HEADER.getMessage();

        log.warn(LOG_CODE_FORMAT, e.getClass().getSimpleName(), code, message);

        ErrorResponse response = ErrorResponse.of(message,code);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationException(ApplicationException e) {

        final String errorCode = e.getErrorCode();
        final String exceptionClassName = e.getClass().getSimpleName();
        final String message = e.getMessage();
        ErrorResponse errorResponse = null;

        if (e.getErrors() != null) {
            log.warn(LOG_CODE_FORMAT, exceptionClassName, errorCode, "@valid");
            errorResponse = ErrorResponse.of(message, errorCode, e.getErrors());
        } else {
            log.warn(LOG_CODE_FORMAT, exceptionClassName, errorCode, message);
            errorResponse = ErrorResponse.of(message, errorCode);
        }

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        final String message = MISSING_REQUEST_PARAM.getMessage();
        final String code = MISSING_REQUEST_PARAM.getCode();

        log.warn(LOG_CODE_FIELD_FORMAT,
                e.getClass().getSimpleName(),
                code,
                e.getParameterName(),
                message);

        ErrorResponse response = ErrorResponse.of(message,code);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constraintViolationException(ConstraintViolationException e) {

        log.warn(LOG_FIELD_FORMAT,
                e.getClass().getSimpleName(),
                e.getConstraintViolations(),
                e.getMessage());

        ErrorResponse response = ErrorResponse.of(BINDING_EXCEPTION.getMessage(), BINDING_EXCEPTION.getCode());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException e) {

        log.warn(LOG_FORMAT,
                e.getClass().getSimpleName(),
                e.getMessage());

        ErrorResponse response = ErrorResponse.of(BINDING_EXCEPTION.getMessage(), BINDING_EXCEPTION.getCode());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {

        log.warn(LOG_FORMAT,
                e.getClass().getSimpleName(),
                e.getMessage());

        ErrorResponse response = ErrorResponse.of(NOT_SUPPORT_METHOD.getMessage(), NOT_SUPPORT_METHOD.getCode());
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(response);
    }


}
