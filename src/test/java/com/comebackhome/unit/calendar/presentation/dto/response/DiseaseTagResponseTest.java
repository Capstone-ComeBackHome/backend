package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagResponseDto;
import com.comebackhome.calendar.presentation.dto.response.DiseaseTagResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.calendar.domain.diseasetag.DiseaseType.HEAD;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseTagResponseTest {

    @Test
    void 정적_메서드_from_으로_생성() throws Exception{
        //given

        DiseaseTagResponseDto diseaseTagResponseDto = givenDiseaseTagResponseDto(HEAD, "두통");

        //when
        DiseaseTagResponse result = DiseaseTagResponse.from(diseaseTagResponseDto);

        //then
        assertThat(result.getDiseaseType()).isEqualTo(diseaseTagResponseDto.getDiseaseType());
        assertThat(result.getName()).isEqualTo(diseaseTagResponseDto.getName());
    }
}
