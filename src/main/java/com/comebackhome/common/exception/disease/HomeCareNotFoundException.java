package com.comebackhome.common.exception.disease;

import org.springframework.http.HttpStatus;

public class HomeCareNotFoundException extends DiseaseException{

    private static final String MESSAGE = "질병 홈케어가 존재하지 않습니다.";
    private static final String CODE = "HOMECARE-400";

    public HomeCareNotFoundException() {
        super(CODE, HttpStatus.BAD_REQUEST, MESSAGE);
    }
}
