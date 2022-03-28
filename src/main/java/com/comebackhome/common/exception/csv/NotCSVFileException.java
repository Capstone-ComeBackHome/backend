package com.comebackhome.common.exception.csv;

import org.springframework.http.HttpStatus;

public class NotCSVFileException extends CSVException{

    private static final String MESSAGE = "해당 파일은 CSV 파일이 아닙니다.";
    private static final String CODE = "DISEASE-400";

    public NotCSVFileException() {
        super(CODE, HttpStatus.BAD_REQUEST, MESSAGE);
    }
}
