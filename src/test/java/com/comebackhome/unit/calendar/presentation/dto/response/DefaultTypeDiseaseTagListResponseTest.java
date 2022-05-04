package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagListResponseDto;
import com.comebackhome.calendar.presentation.dto.response.DefaultTypeDiseaseTagListResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagListResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DefaultTypeDiseaseTagListResponseTest {

    @Test
    void 정적_메서드_from_으로_생성() throws Exception{
        //given
        DiseaseTagListResponseDto diseaseTagListResponseDto = givenDiseaseTagListResponseDto();

        //when
        DefaultTypeDiseaseTagListResponse result = DefaultTypeDiseaseTagListResponse.from(diseaseTagListResponseDto);

        //then
        assertThat(result.getHeadDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getBronchusDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getChestDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getStomachDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getLimbDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getSkinDiseaseTagList().size()).isEqualTo(1);
    }
}
