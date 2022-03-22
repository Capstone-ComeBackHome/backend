package com.comebackhome.unit.calendar.application.dto;

import com.comebackhome.calendar.application.dto.DiseaseTagDto;
import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.dto.DiseaseTagQueryDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagQueryDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseTagDtoTest {

    @Test
    void 정적_메서드_from_으로_생성() throws Exception{
        //given
        DiseaseTagQueryDto diseaseTagQueryDto = givenDiseaseTagQueryDto(DiseaseType.HEAD,"두통");

        //when
        DiseaseTagDto result = DiseaseTagDto.from(diseaseTagQueryDto);

        //then
        assertThat(result.getDiseaseType()).isEqualTo(diseaseTagQueryDto.getDiseaseType());
        assertThat(result.getName()).isEqualTo(diseaseTagQueryDto.getName());
    }
}
