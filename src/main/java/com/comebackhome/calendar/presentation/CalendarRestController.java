package com.comebackhome.calendar.presentation;

import com.comebackhome.calendar.application.CalendarCommandUseCase;
import com.comebackhome.calendar.application.CalendarQueryUseCase;
import com.comebackhome.calendar.presentation.dto.ScheduleModifyRequest;
import com.comebackhome.calendar.presentation.dto.ScheduleResponse;
import com.comebackhome.calendar.presentation.dto.ScheduleSaveRequest;
import com.comebackhome.calendar.presentation.dto.SimpleScheduleResponseList;
import com.comebackhome.common.LoginUser;
import com.comebackhome.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping("/api/v1/calendars")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class CalendarRestController {

    private final CalendarCommandUseCase calendarCommandUseCase;
    private final CalendarQueryUseCase calendarQueryUseCase;

    @PostMapping
    public ResponseEntity<Void> saveMySchedule(@Validated @RequestBody ScheduleSaveRequest scheduleSaveRequest,
                                               @LoginUser User user){
        calendarCommandUseCase.saveMySchedule(scheduleSaveRequest.toScheduleSaveRequestDto(user.getId()));
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteMySchedule(@PathVariable Long scheduleId,
                                                 @LoginUser User user){
        calendarCommandUseCase.deleteSchedule(scheduleId,user.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<SimpleScheduleResponseList> getMyMonthSchedule(@RequestParam YearMonth yearMonth,
                                                                         @LoginUser User user){

        return ResponseEntity.ok(SimpleScheduleResponseList
                .from(calendarQueryUseCase.getMyMonthSchedule(yearMonth,user.getId())));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getMySchedule(@PathVariable Long scheduleId,
                                                          @LoginUser User user){
        return ResponseEntity.ok(ScheduleResponse.from(calendarQueryUseCase.getMySchedule(scheduleId, user.getId())));
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Void> modifyMySchedule(@PathVariable Long scheduleId,
                                                 @Validated @RequestBody ScheduleModifyRequest scheduleModifyRequest,
                                                          @LoginUser User user){
        calendarCommandUseCase.modifyMySchedule(scheduleId, user.getId(), scheduleModifyRequest.toScheduleModifyRequestDto());
        return ResponseEntity.ok().build();
    }

}
