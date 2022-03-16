package com.comebackhome.common.exception.disease;

import org.springframework.http.HttpStatus;

public class DiseaseNotFoundException extends DiseaseException{

    private static final String MESSAGE = "존재하지 않는 질병입니다.";
    private static final String CODE = "DISEASE-400";

    public DiseaseNotFoundException() {
        super(CODE, HttpStatus.BAD_REQUEST, MESSAGE);
    }
}
