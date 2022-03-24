package com.comebackhome.unit.calendar.application.dto;

import com.comebackhome.calendar.application.dto.response.DiseaseTagDto;
import com.comebackhome.calendar.domain.DiseaseTag;
import com.comebackhome.calendar.domain.dto.DiseaseTagQueryDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.calendar.domain.DiseaseType.HEAD;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTag;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagQueryDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseTagDtoTest {

    @Test
    void 정적_메서드_from_으로_생성_인자_diseaseTagQueryDto() throws Exception{
        //given
        DiseaseTagQueryDto diseaseTagQueryDto = givenDiseaseTagQueryDto(HEAD,"두통");

        //when
        DiseaseTagDto result = DiseaseTagDto.from(diseaseTagQueryDto);

        //then
        assertThat(result.getDiseaseType()).isEqualTo(diseaseTagQueryDto.getDiseaseType());
        assertThat(result.getName()).isEqualTo(diseaseTagQueryDto.getName());
    }

    @Test
    void 정적_메서드_from_으로_생성_인자_diseaseTag() throws Exception{
        //given
        DiseaseTag diseaseTag = givenDiseaseTag(HEAD, "두통");

        //when
        DiseaseTagDto result = DiseaseTagDto.from(diseaseTag);

        //then
        assertThat(result.getDiseaseType()).isEqualTo(diseaseTag.getDiseaseType());
        assertThat(result.getName()).isEqualTo(diseaseTag.getName());
    }
}
