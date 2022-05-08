package com.comebackhome.unit.calendar.domain.service;

import com.comebackhome.calendar.domain.schedule.Schedule;
import com.comebackhome.calendar.domain.schedule.repository.ScheduleRepository;
import com.comebackhome.calendar.domain.schedule.service.CalendarQueryService;
import com.comebackhome.calendar.domain.schedule.service.dto.response.ScheduleResponseDto;
import com.comebackhome.calendar.domain.schedule.service.dto.response.SimpleScheduleResponseDto;
import com.comebackhome.common.exception.schedule.ScheduleNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenSchedule;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenSimpleScheduleResponseDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CalendarQueryServiceTest {

    @InjectMocks CalendarQueryService calendarQueryService;
    @Mock ScheduleRepository scheduleRepository;

    @Test
    void 특정_월_스케줄_가져오기() throws Exception{
        //given
        SimpleScheduleResponseDto simpleScheduleResponseDto = givenSimpleScheduleResponseDto(1L, LocalDate.now(), 3);
        given(scheduleRepository.findByYearMonthAndUserId(any(),any()))
                .willReturn(List.of(simpleScheduleResponseDto));

        //when
        List<SimpleScheduleResponseDto> result = calendarQueryService.getMyMonthSchedule(any(), any());

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getDiseaseTagCount()).isEqualTo(simpleScheduleResponseDto.getDiseaseTagCount());
        assertThat(result.get(0).getLocalDate()).isEqualTo(simpleScheduleResponseDto.getLocalDate());
        assertThat(result.get(0).getScheduleId()).isEqualTo(simpleScheduleResponseDto.getScheduleId());
    }

    @Test
    void 특정_스케줄_가져오기() throws Exception{
        //given
        Schedule schedule = givenSchedule();

        given(scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(any(),any()))
                .willReturn(Optional.of(schedule));

        //when
        ScheduleResponseDto result = calendarQueryService.getMySchedule(any(), any());

        //then
        assertThat(result.getScheduleId()).isEqualTo(schedule.getId());
        assertThat(result.getLocalDate()).isEqualTo(schedule.getLocalDate());
        assertThat(result.getDiseaseTagResponseDtoList().size()).isEqualTo(schedule.getScheduleDiseaseTagList().size());
        assertThat(result.getDailyNote()).isEqualTo(schedule.getDailyNote());
        assertThat(result.getPainType()).isEqualTo(schedule.getPainType().name());
    }

    @Test
    void 존재하지_않는_특정_스케줄_가져오기() throws Exception{
        //given
        given(scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(any(),any()))
                .willReturn(Optional.empty());

        //when then
        assertThatThrownBy(
                () -> calendarQueryService.getMySchedule(any(),any()))
                .isInstanceOf(ScheduleNotFoundException.class);
    }
}
