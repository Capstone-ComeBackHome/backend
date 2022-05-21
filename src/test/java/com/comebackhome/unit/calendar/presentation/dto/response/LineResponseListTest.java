package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.schedule.service.dto.response.LineResponseDto;
import com.comebackhome.calendar.presentation.dto.response.LineResponseList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenLineResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class LineResponseListTest {

    @Test
    void 정적_메서드_from으로_생성() {
        //given
        LineResponseDto lineResponseDto = givenLineResponseDto();

        //when
        LineResponseList result = LineResponseList.from(lineResponseDto);

        //then
        assertThat(result.getTop1().size()).isEqualTo(lineResponseDto.getTop1().size());
        assertThat(result.getTop2().size()).isEqualTo(lineResponseDto.getTop2().size());
        assertThat(result.getTop3().size()).isEqualTo(lineResponseDto.getTop3().size());
        Assertions.assertThat(result.getBefore3MonthDate()).isEqualTo(lineResponseDto.getBefore3MonthDate());
    }
}

