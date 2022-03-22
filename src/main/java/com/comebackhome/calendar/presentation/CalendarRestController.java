package com.comebackhome.calendar.presentation;

import com.comebackhome.calendar.application.CalendarCommandUseCase;
import com.comebackhome.calendar.presentation.dto.ScheduleSaveRequest;
import com.comebackhome.common.LoginUser;
import com.comebackhome.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calendars")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class CalendarRestController {

    private final CalendarCommandUseCase calendarCommandUseCase;

    @PostMapping
    public ResponseEntity<Void> saveMySchedule(@Validated @RequestBody ScheduleSaveRequest scheduleSaveRequest,
                                               @LoginUser User user){
        calendarCommandUseCase.saveMySchedule(scheduleSaveRequest.toScheduleSaveRequestDto(user.getId()));
        return ResponseEntity.ok().build();
    }
}
