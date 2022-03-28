package com.comebackhome.common.exception.csv;

import com.comebackhome.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class CSVException extends ApplicationException {

    protected CSVException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
