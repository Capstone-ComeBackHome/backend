package com.comebackhome.unit.calendar.presentation.dto;

import com.comebackhome.calendar.application.dto.response.DiseaseTagDto;
import com.comebackhome.calendar.presentation.dto.response.DiseaseTagResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.calendar.domain.DiseaseType.HEAD;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseTagResponseTest {

    @Test
    void 정적_메서드_from_으로_생성() throws Exception{
        //given
        DiseaseTagDto diseaseTagDto = givenDiseaseTagDto(HEAD, "두통");

        //when
        DiseaseTagResponse result = DiseaseTagResponse.from(diseaseTagDto);

        //then
        assertThat(result.getDiseaseType()).isEqualTo(diseaseTagDto.getDiseaseType());
        assertThat(result.getName()).isEqualTo(diseaseTagDto.getName());
    }
}
