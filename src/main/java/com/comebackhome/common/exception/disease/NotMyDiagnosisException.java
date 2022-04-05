package com.comebackhome.common.exception.disease;

import org.springframework.http.HttpStatus;

public class NotMyDiagnosisException extends DiseaseException{

    private static final String MESSAGE = "나의 진단내역이 아닙니다.";
    private static final String CODE = "DIAGNOSIS-403";

    public NotMyDiagnosisException() {
        super(CODE, HttpStatus.FORBIDDEN, MESSAGE);
    }
}
