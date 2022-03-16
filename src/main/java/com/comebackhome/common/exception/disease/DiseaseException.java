package com.comebackhome.common.exception.disease;

import com.comebackhome.common.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class DiseaseException extends ApplicationException {

    protected DiseaseException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
