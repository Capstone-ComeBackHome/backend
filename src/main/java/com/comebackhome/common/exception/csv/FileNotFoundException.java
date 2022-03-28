package com.comebackhome.common.exception.csv;

import org.springframework.http.HttpStatus;

public class FileNotFoundException extends CSVException {

    private static final String MESSAGE = "파일이 존재하지 않습니다.";
    private static final String CODE = "File-400";

    public FileNotFoundException() {
        super(CODE, HttpStatus.BAD_REQUEST, MESSAGE);
    }
}
