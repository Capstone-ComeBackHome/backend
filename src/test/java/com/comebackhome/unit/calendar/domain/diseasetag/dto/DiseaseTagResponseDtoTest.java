package com.comebackhome.unit.calendar.domain.diseasetag.dto;

import com.comebackhome.calendar.domain.diseasetag.DiseaseTag;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagResponseDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.calendar.domain.diseasetag.DiseaseType.HEAD;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTag;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseTagResponseDtoTest {

    @Test
    void 정적_메서드_from_으로_생성_인자_diseaseTagQueryDto() throws Exception{
        //given
        DiseaseTag diseaseTag = givenDiseaseTag(HEAD, "두통");

        //when
        DiseaseTagResponseDto result = DiseaseTagResponseDto.from(diseaseTag);

        //then
        assertThat(result.getDiseaseType()).isEqualTo(diseaseTag.getDiseaseType());
        assertThat(result.getName()).isEqualTo(diseaseTag.getName());
    }

    @Test
    void 정적_메서드_from_으로_생성_인자_diseaseTag() throws Exception{
        //given
        DiseaseTag diseaseTag = givenDiseaseTag(HEAD, "두통");

        //when
        DiseaseTagResponseDto result = DiseaseTagResponseDto.from(diseaseTag);

        //then
        assertThat(result.getDiseaseType()).isEqualTo(diseaseTag.getDiseaseType());
        assertThat(result.getName()).isEqualTo(diseaseTag.getName());
    }
}
