package com.comebackhome.common.exception.schedule;

import com.comebackhome.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class ScheduleException extends ApplicationException {

    protected ScheduleException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
