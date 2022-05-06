package com.comebackhome.common.exception;

import com.comebackhome.common.CommonResponse;
import com.comebackhome.common.exception.csv.CSVException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestValueException;
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




    /**
     * 개발자가 예상 가능한 예외
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<CommonResponse> applicationException(ApplicationException e) {

        final String errorCode = e.getErrorCode();
        final String exceptionClassName = e.getClass().getSimpleName();
        final String message = e.getMessage();
        CommonResponse errorResponse = null;

        if (e.getErrors() != null) {
            log.info(LOG_CODE_FORMAT, exceptionClassName, errorCode, "@valid");
            errorResponse = CommonResponse.failOf(message, errorCode, e.getErrors());
        } else {
            log.info(LOG_CODE_FORMAT, exceptionClassName, errorCode, message);
            errorResponse = CommonResponse.failOf(message, errorCode);
        }

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(errorResponse);
    }


    /**
     * 요청 binding validation 에러
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommonResponse> bindException(BindException e) {

        final String code = BINDING_EXCEPTION.getCode();
        final String message = BINDING_EXCEPTION.getMessage();

        log.info(LOG_CODE_FORMAT, e.getClass().getSimpleName(), code, message);

        CommonResponse response = CommonResponse.failOf(message, code, e.getBindingResult());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * 요청 헤더, multi-part-form 등 필수 요청값이 안들어온 경우
     */
    @ExceptionHandler(MissingRequestValueException.class)
    public ResponseEntity<CommonResponse> handleMissingRequestValueException(MissingRequestValueException e) {

        final String code = MISSING_REQUEST_VALUE.getCode();
        final String message = MISSING_REQUEST_VALUE.getMessage();

        log.info(LOG_CODE_FORMAT, e.getClass().getSimpleName(), code, message,e);

        CommonResponse response = CommonResponse.failOf(message,code);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * @RequestBody, @RequestPart를 제외한 validation 애노테이션 적용되는 예외
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CommonResponse> constraintViolationException(ConstraintViolationException e) {

        log.info(LOG_FIELD_FORMAT,
                e.getClass().getSimpleName(),
                e.getConstraintViolations(),
                e.getMessage());

        CommonResponse response = CommonResponse.failOf(BINDING_EXCEPTION.getMessage(), BINDING_EXCEPTION.getCode());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<CommonResponse> httpMessageNotReadableException(HttpMessageNotReadableException e) {
//
//        log.warn(LOG_FORMAT,
//                e.getClass().getSimpleName(),
//                e.getMessage());
//
//        CommonResponse response = CommonResponse.failOf(BINDING_EXCEPTION.getMessage(), BINDING_EXCEPTION.getCode());
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(response);
//    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CommonResponse> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {

        log.info(LOG_FORMAT,
                e.getClass().getSimpleName(),
                e.getMessage());

        CommonResponse response = CommonResponse.failOf(NOT_SUPPORT_METHOD.getMessage(), NOT_SUPPORT_METHOD.getCode());
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(response);
    }


    @ExceptionHandler(CSVException.class)
    public ResponseEntity<CommonResponse> csvException(CSVException e) {

        final String code = BINDING_EXCEPTION.getCode();
        final String message = BINDING_EXCEPTION.getMessage();

        log.info(LOG_CODE_FORMAT, e.getClass().getSimpleName(), code, message,e);

        CommonResponse response = CommonResponse.failOf(message, code);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}
