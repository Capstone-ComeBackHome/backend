package com.comebackhome.unit.calendar.domain.schedule.service.dto.response;

import com.comebackhome.calendar.domain.schedule.PainType;
import com.comebackhome.calendar.domain.schedule.repository.dto.LineQueryDto;
import com.comebackhome.calendar.domain.schedule.service.dto.response.LineResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LineResponseDtoTest {

    @Test
    void 정적_메서드_from_으로_생성() {
        //given
        List<List<LineQueryDto>> ls = List.of(
                List.of(
                        new LineQueryDto(LocalDate.now().minusDays(2), PainType.BAD, "재채기"),
                        new LineQueryDto(LocalDate.now().minusDays(1), PainType.BAD, "재채기"),
                        new LineQueryDto(LocalDate.now(), PainType.BAD, "재채기")
                ),
                List.of(
                        new LineQueryDto(LocalDate.now().minusDays(2), PainType.BAD, "코막힘"),
                        new LineQueryDto(LocalDate.now().minusDays(1), PainType.BAD, "코막힘")
                ),
                List.of(new LineQueryDto(LocalDate.now(), PainType.BAD, "인후염"))
        );

        //when
        LineResponseDto result = LineResponseDto.from(ls);

        //then
        Assertions.assertThat(result.getTop1().size()).isEqualTo(3);
        Assertions.assertThat(result.getTop2().size()).isEqualTo(2);
        Assertions.assertThat(result.getTop3().size()).isEqualTo(1);
        Assertions.assertThat(result.getBefore3MonthDate()).isEqualTo(LocalDate.now().minusMonths(3).toString());
    }

    @Test
    void 정적_메서드_from_으로_생성_빈_리스트인_경우() {
        //given
        List<List<LineQueryDto>> ls = new ArrayList<>();

        //when
        LineResponseDto result = LineResponseDto.from(ls);

        //then
        Assertions.assertThat(result.getTop1().isEmpty()).isTrue();
        Assertions.assertThat(result.getTop2().isEmpty()).isTrue();
        Assertions.assertThat(result.getTop3().isEmpty()).isTrue();
        Assertions.assertThat(result.getBefore3MonthDate()).isEqualTo(LocalDate.now().minusMonths(3).toString());
    }
}
