package com.comebackhome.unit.calendar.application;

import com.comebackhome.calendar.application.CalendarQueryService;
import com.comebackhome.calendar.application.dto.SimpleScheduleResponseDto;
import com.comebackhome.calendar.domain.dto.SimpleScheduleQueryDto;
import com.comebackhome.calendar.domain.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenSimpleScheduleQueryDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CalendarQueryServiceTest {

    @InjectMocks CalendarQueryService calendarQueryService;
    @Mock ScheduleRepository scheduleRepository;

    @Test
    void 특정_월_스케줄_가져오기() throws Exception{
        //given
        SimpleScheduleQueryDto simpleScheduleQueryDto = givenSimpleScheduleQueryDto(1L, LocalDate.now(), 3);
        given(scheduleRepository.findByYearMonthAndUserId(any(),any()))
                .willReturn(List.of(simpleScheduleQueryDto));

        //when
        List<SimpleScheduleResponseDto> result = calendarQueryService.getMyMonthSchedule(any(), any());

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getDiseaseTagCount()).isEqualTo(simpleScheduleQueryDto.getDiseaseTagCount());
        assertThat(result.get(0).getLocalDate()).isEqualTo(simpleScheduleQueryDto.getLocalDate());
        assertThat(result.get(0).getScheduleId()).isEqualTo(simpleScheduleQueryDto.getScheduleId());
    }
}
