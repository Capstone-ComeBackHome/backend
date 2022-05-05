package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DefaultTypeDiseaseTagListResponseDto;
import com.comebackhome.calendar.presentation.dto.response.DefaultTypeDiseaseTagListResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenDefaultTypeDiseaseTagListResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DefaultTypeDiseaseTagListResponseTest {

    @Test
    void 정적_메서드_from_으로_생성() throws Exception{
        //given
        DefaultTypeDiseaseTagListResponseDto defaultTypeDiseaseTagListResponseDto = givenDefaultTypeDiseaseTagListResponseDto();

        //when
        DefaultTypeDiseaseTagListResponse result = DefaultTypeDiseaseTagListResponse.from(defaultTypeDiseaseTagListResponseDto);

        //then
        assertThat(result.getHead().getDiseaseTagNameList().size()).isEqualTo(1);
        assertThat(result.getHead().getDiseaseTypeDescription()).isEqualTo(DiseaseType.HEAD.getDescription());
        assertThat(result.getBronchus().getDiseaseTagNameList().size()).isEqualTo(1);
        assertThat(result.getBronchus().getDiseaseTypeDescription()).isEqualTo(DiseaseType.BRONCHUS.getDescription());
        assertThat(result.getChest().getDiseaseTagNameList().size()).isEqualTo(1);
        assertThat(result.getChest().getDiseaseTypeDescription()).isEqualTo(DiseaseType.CHEST.getDescription());
        assertThat(result.getStomach().getDiseaseTagNameList().size()).isEqualTo(1);
        assertThat(result.getStomach().getDiseaseTypeDescription()).isEqualTo(DiseaseType.STOMACH.getDescription());
        assertThat(result.getLimb().getDiseaseTagNameList().size()).isEqualTo(1);
        assertThat(result.getLimb().getDiseaseTypeDescription()).isEqualTo(DiseaseType.LIMB.getDescription());
        assertThat(result.getSkin().getDiseaseTagNameList().size()).isEqualTo(1);
        assertThat(result.getSkin().getDiseaseTypeDescription()).isEqualTo(DiseaseType.SKIN.getDescription());
    }
}
