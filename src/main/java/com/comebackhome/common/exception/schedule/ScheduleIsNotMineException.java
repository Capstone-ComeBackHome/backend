package com.comebackhome.common.exception.schedule;

import org.springframework.http.HttpStatus;

public class ScheduleIsNotMineException extends ScheduleException{

    private static final String MESSAGE = "자신의 스케줄이 아닙니다.";
    private static final String CODE = "Schedule-403";

    public ScheduleIsNotMineException() {
        super(CODE, HttpStatus.FORBIDDEN, MESSAGE);
    }
}
