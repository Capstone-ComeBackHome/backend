package com.comebackhome.unit.calendar.application;

import com.comebackhome.calendar.application.CalendarFacade;
import com.comebackhome.calendar.domain.schedule.service.CalendarCommandUseCase;
import com.comebackhome.calendar.domain.schedule.service.CalendarQueryUseCase;
import com.comebackhome.calendar.domain.schedule.service.dto.response.BubbleResponseDto;
import com.comebackhome.calendar.domain.schedule.service.dto.response.LineResponseDto;
import com.comebackhome.calendar.domain.schedule.service.dto.response.ScheduleResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.comebackhome.support.helper.CalendarGivenHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CalendarFacadeTest {

    @InjectMocks CalendarFacade calendarFacade;
    @Mock CalendarCommandUseCase calendarCommandUseCase;
    @Mock CalendarQueryUseCase calendarQueryUseCase;

    @Test
    void 월_스케줄_정보_가져오기() {
        //given
        List<ScheduleResponseDto> dto = List.of(
                givenScheduleResponseDto(),
                givenScheduleResponseDto()
        );
        given(calendarQueryUseCase.getMyMonthSchedule(any(),any())).willReturn(dto);

        //when
        List<ScheduleResponseDto> result = calendarFacade.getMyMonthSchedule(any(), any());

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getPainType()).isEqualTo(dto.get(0).getPainType());
        assertThat(result.get(0).getDailyNote()).isEqualTo(dto.get(0).getDailyNote());
        assertThat(result.get(0).getScheduleId()).isEqualTo(dto.get(0).getScheduleId());
        assertThat(result.get(0).getLocalDate()).isEqualTo(dto.get(0).getLocalDate());
        assertThat(result.get(0).getDiseaseTagResponseDtoList().size()).isEqualTo(dto.get(0).getDiseaseTagResponseDtoList().size());
    }

    @Test
    void 스케줄_정보_가져오기() {
        //given
        ScheduleResponseDto dto = givenScheduleResponseDto();
        given(calendarQueryUseCase.getMySchedule(any(),any())).willReturn(dto);

        //when
        ScheduleResponseDto result = calendarFacade.getMySchedule(any(), any());

        //then
        assertThat(result.getScheduleId()).isEqualTo(dto.getScheduleId());
        assertThat(result.getLocalDate()).isEqualTo(dto.getLocalDate());
        assertThat(result.getDailyNote()).isEqualTo(dto.getDailyNote());
        assertThat(result.getPainType()).isEqualTo(dto.getPainType());
        assertThat(result.getDiseaseTagResponseDtoList()).isEqualTo(dto.getDiseaseTagResponseDtoList());
    }

    @Test
    void bubble_그래프_데이터_가져오기() {
        //given
        List<BubbleResponseDto> bubbleResponseDtoList = givenBubbleResponseDtoList();
        given(calendarQueryUseCase.getBubbleStatisticData(any())).willReturn(bubbleResponseDtoList);

        //when
        List<BubbleResponseDto> result = calendarFacade.getBubbleStatisticData(any());

        //then
        assertThat(result).isEqualTo(bubbleResponseDtoList);
    }

    @Test
    void line_그래프_데이터_가져오기() {
        //given
        LineResponseDto lineResponseDto = givenLineResponseDto();
        given(calendarQueryUseCase.getLineStatisticDate(any())).willReturn(lineResponseDto);

        //when
        LineResponseDto result = calendarFacade.getLineStatisticDate(any());

        //then
        assertThat(result.getTop1()).isEqualTo(lineResponseDto.getTop1());
        assertThat(result.getTop2()).isEqualTo(lineResponseDto.getTop2());
        assertThat(result.getTop3()).isEqualTo(lineResponseDto.getTop3());
    }

}
