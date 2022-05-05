package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagListResponseDto;
import com.comebackhome.calendar.presentation.dto.response.DiseaseTagListResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.comebackhome.calendar.domain.diseasetag.DiseaseType.HEAD;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagListResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseTagListResponseTest {

    @Test
    void 정적_메서드_from_으로_생성() throws Exception{
        //given

        DiseaseTagListResponseDto dto = givenDiseaseTagListResponseDto(List.of("두통"),HEAD);

        //when
        DiseaseTagListResponse result = DiseaseTagListResponse.from(dto);

        //then
        assertThat(result.getDiseaseTypeDescription()).isEqualTo(dto.getDiseaseTypeDescription());
        assertThat(result.getDiseaseTagNameList().size()).isEqualTo(1);
    }
}
