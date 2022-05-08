package com.comebackhome.unit.calendar.domain.schedule.service;

import com.comebackhome.calendar.domain.schedule.repository.ScheduleDiseaseTagRepository;
import com.comebackhome.calendar.domain.schedule.repository.ScheduleRepository;
import com.comebackhome.calendar.domain.schedule.service.CalendarCommandService;
import com.comebackhome.calendar.domain.schedule.service.ScheduleDiseaseSeriesFactory;
import com.comebackhome.common.exception.schedule.ScheduleNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenScheduleModifyRequestDto;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class CalendarCommandServiceTest {

    @InjectMocks CalendarCommandService calendarCommandService;
    @Mock ScheduleRepository scheduleRepository;
    @Mock ScheduleDiseaseTagRepository scheduleDiseaseTagRepository;
    @Mock ScheduleDiseaseSeriesFactory scheduleDiseaseSeriesFactory;

    @Test
    void scheduleId로_스케줄_삭제() throws Exception{
        //given
        given(scheduleRepository.existsByIdAndUserId(any(),any()))
                .willReturn(true);

        //when
        calendarCommandService.deleteSchedule(any(),any());

        //then
        then(scheduleRepository).should().existsByIdAndUserId(any(),any());
        then(scheduleRepository).should().deleteById(any());
    }

    @Test
    void 내_스케줄_중_존재하지_않는_스케줄의_scheduleId_삭제() throws Exception{
        //given
        given(scheduleRepository.existsByIdAndUserId(any(),any()))
                .willReturn(false);

        //when then
        assertThatThrownBy(
                () -> calendarCommandService.deleteSchedule(any(),any()))
                .isInstanceOf(ScheduleNotFoundException.class);
    }

    @Test
    void 내_스케줄_중_존재하지_않는_스케줄의_scheduleId로_수정_요청() throws Exception{
        //given
        given(scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(any(),any()))
                .willReturn(Optional.empty());

        //when then
        assertThatThrownBy(
                () -> calendarCommandService.modifyMySchedule(any(),any(),givenScheduleModifyRequestDto()))
                .isInstanceOf(ScheduleNotFoundException.class);
    }

}
