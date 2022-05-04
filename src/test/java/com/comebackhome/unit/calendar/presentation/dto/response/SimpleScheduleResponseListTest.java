package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.service.dto.response.SimpleScheduleResponseDto;
import com.comebackhome.calendar.presentation.dto.response.SimpleScheduleResponse;
import com.comebackhome.calendar.presentation.dto.response.SimpleScheduleResponseList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenSimpleScheduleResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleScheduleResponseListTest {

    @Test
    void 정적_메서드_from_으로_생성() throws Exception{
        //given
        SimpleScheduleResponseDto simpleScheduleResponseDto = givenSimpleScheduleResponseDto(1L, LocalDate.now(), 3);

        //when
        List<SimpleScheduleResponse> result = SimpleScheduleResponseList.from(List.of(simpleScheduleResponseDto)).getSimpleScheduleResponseList();

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getScheduleId()).isEqualTo(simpleScheduleResponseDto.getScheduleId());
        assertThat(result.get(0).getLocalDate()).isEqualTo(simpleScheduleResponseDto.getLocalDate());
        assertThat(result.get(0).getDiseaseTagCount()).isEqualTo(simpleScheduleResponseDto.getDiseaseTagCount());
    }
}
