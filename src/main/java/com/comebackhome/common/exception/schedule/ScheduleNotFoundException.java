package com.comebackhome.common.exception.schedule;

import org.springframework.http.HttpStatus;

public class ScheduleNotFoundException extends ScheduleException{

    private static final String MESSAGE = "존재하지 않는 스케줄입니다.";
    private static final String CODE = "Schedule-400";

    public ScheduleNotFoundException() {
        super(CODE, HttpStatus.BAD_REQUEST, MESSAGE);
    }
}
