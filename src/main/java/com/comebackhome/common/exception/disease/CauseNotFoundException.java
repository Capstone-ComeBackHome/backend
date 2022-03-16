package com.comebackhome.common.exception.disease;

import org.springframework.http.HttpStatus;

public class CauseNotFoundException extends DiseaseException{
    private static final String MESSAGE = "질병 원인이 존재하지 않습니다.";
    private static final String CODE = "CAUSE-400";

    public CauseNotFoundException() {
        super(CODE, HttpStatus.BAD_REQUEST, MESSAGE);
    }

}
