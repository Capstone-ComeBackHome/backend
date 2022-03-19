package com.comebackhome.unit.calendar.presentation.dto;

import com.comebackhome.calendar.application.dto.DiseaseTagResponseDto;
import com.comebackhome.calendar.presentation.dto.DiseaseTagResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseTagResponseTest {

    @Test
    void 정적_메서드_from_으로_생성() throws Exception{
        //given
        DiseaseTagResponseDto diseaseTagResponseDto = givenDiseaseTagResponseDto();

        //when
        DiseaseTagResponse result = DiseaseTagResponse.from(diseaseTagResponseDto);

        //then
        assertThat(result.getHeadDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getBronchusDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getChestDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getStomachDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getLimbDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getSkinDiseaseTagList().size()).isEqualTo(1);
    }
}
