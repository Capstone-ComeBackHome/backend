package com.comebackhome.common.exception.disease;

import org.springframework.http.HttpStatus;

public class DiagnosisNotFoundException extends DiseaseException{

    private static final String MESSAGE = "존재하지 않는 진단입니다.";
    private static final String CODE = "DIAGNOSIS-400";

    public DiagnosisNotFoundException() {
        super(CODE, HttpStatus.BAD_REQUEST, MESSAGE);
    }
}
