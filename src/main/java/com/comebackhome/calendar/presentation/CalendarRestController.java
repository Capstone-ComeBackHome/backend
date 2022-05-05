package com.comebackhome.calendar.presentation;

import com.comebackhome.calendar.application.CalendarFacade;
import com.comebackhome.calendar.presentation.dto.request.ScheduleModifyRequest;
import com.comebackhome.calendar.presentation.dto.request.ScheduleSaveRequest;
import com.comebackhome.calendar.presentation.dto.response.ScheduleResponse;
import com.comebackhome.calendar.presentation.dto.response.SimpleScheduleResponseList;
import com.comebackhome.common.CommonResponse;
import com.comebackhome.common.LoginUser;
import com.comebackhome.common.exception.ValidatedException;
import com.comebackhome.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping(value = "/api/v1/calendars")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class CalendarRestController {

    private final CalendarFacade calendarFacade;

    @PostMapping
    public ResponseEntity<CommonResponse> saveMySchedule(@Validated @RequestBody ScheduleSaveRequest scheduleSaveRequest,
                                               @LoginUser User user){
        calendarFacade.saveMySchedule(scheduleSaveRequest.toScheduleSaveRequestDto(user.getId()));
        return ResponseEntity.ok(CommonResponse.success());
    }


    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<CommonResponse> deleteMySchedule(@PathVariable Long scheduleId,
                                                 @LoginUser User user){
        calendarFacade.deleteSchedule(scheduleId,user.getId());
        return ResponseEntity.ok(CommonResponse.success());
    }

    @GetMapping
    public ResponseEntity<CommonResponse<SimpleScheduleResponseList>> getMyMonthSchedule(@RequestParam YearMonth yearMonth,
                                                                                        @LoginUser User user){

        SimpleScheduleResponseList simpleScheduleResponseList
                = SimpleScheduleResponseList.from(calendarFacade.getMyMonthSchedule(yearMonth, user.getId()));
        return ResponseEntity.ok(CommonResponse.success(simpleScheduleResponseList));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<CommonResponse<ScheduleResponse>> getMySchedule(@PathVariable Long scheduleId,
                                                          @LoginUser User user){
        ScheduleResponse scheduleResponse = ScheduleResponse.from(calendarFacade.getMySchedule(scheduleId, user.getId()));
        return ResponseEntity.ok(CommonResponse.success(scheduleResponse));
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<CommonResponse> modifyMySchedule(@PathVariable Long scheduleId,
                                                 @Validated @RequestBody ScheduleModifyRequest scheduleModifyRequest,
                                                          BindingResult errors,
                                                          @LoginUser User user){
        if (errors.hasErrors()){
            throw new ValidatedException(errors);
        }

        calendarFacade.modifyMySchedule(scheduleId, user.getId(), scheduleModifyRequest.toScheduleModifyRequestDto());
        return ResponseEntity.ok(CommonResponse.success());
    }

}
