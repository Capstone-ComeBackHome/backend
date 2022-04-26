package com.comebackhome.common.exception.csv;

import org.springframework.http.HttpStatus;

public class FileReadException extends CSVException{

    private static final String MESSAGE = "'질병명, 정의, 진료과, 증상, 원인, 치료 ' 순의 csv 파일이 아닙니다.";
    private static final String CODE = "DISEASE-400";

    public FileReadException(Throwable cause) {
        super(CODE, HttpStatus.BAD_REQUEST, MESSAGE,cause);
    }
}
