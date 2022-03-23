package com.comebackhome.unit.calendar.application.dto;

import com.comebackhome.calendar.application.dto.SimpleScheduleResponseDto;
import com.comebackhome.calendar.domain.dto.SimpleScheduleQueryDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenSimpleScheduleQueryDto;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleScheduleResponseDtoTest {

    @Test
    void 정적_메서드_from_으로_생성() throws Exception{
        //given
        SimpleScheduleQueryDto simpleScheduleQueryDto = givenSimpleScheduleQueryDto(1L, LocalDate.now(), 3);

        //when
        SimpleScheduleResponseDto result = SimpleScheduleResponseDto.from(simpleScheduleQueryDto);

        //then
        assertThat(result.getScheduleId()).isEqualTo(simpleScheduleQueryDto.getScheduleId());
        assertThat(result.getLocalDate()).isEqualTo(simpleScheduleQueryDto.getLocalDate());
        assertThat(result.getDiseaseTagCount()).isEqualTo(simpleScheduleQueryDto.getDiseaseTagCount());
    }
}
