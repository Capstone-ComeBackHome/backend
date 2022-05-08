package com.comebackhome.unit.calendar.presentation.dto.request;

import com.comebackhome.calendar.domain.schedule.service.dto.request.DiseaseTagRequestDto;
import com.comebackhome.calendar.presentation.dto.request.DiseaseTagRequest;
import org.junit.jupiter.api.Test;

import static com.comebackhome.calendar.domain.diseasetag.DiseaseType.HEAD;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseTagRequestTest {

    @Test
    void DiseaseTagRequestDto로_전환하기() throws Exception{
        //given
        DiseaseTagRequest diseaseTagRequest = givenDiseaseTagRequest(HEAD, "두통");

        //when
        DiseaseTagRequestDto result = diseaseTagRequest.toDiseaseTagRequestDto();

        //then
        assertThat(result.getDiseaseType()).isEqualTo(diseaseTagRequest.getDiseaseType());
        assertThat(result.getName()).isEqualTo(diseaseTagRequest.getName());
    }
}
