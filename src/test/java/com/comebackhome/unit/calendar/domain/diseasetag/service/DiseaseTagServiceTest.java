package com.comebackhome.unit.calendar.domain.diseasetag.service;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.diseasetag.repository.DiseaseTagRepository;
import com.comebackhome.calendar.domain.diseasetag.service.DiseaseTagService;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagListResponseDto;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagQueryDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DiseaseTagServiceTest {

    @InjectMocks DiseaseTagService diseaseTagService;
    @Mock DiseaseTagRepository diseaseTagRepository;

    @Test
    void getDiseaseTagExcludeCustomType() throws Exception{
        //given
        List<DiseaseTagResponseDto> diseaseTagList = createDiseaseTagList();
        given(diseaseTagRepository.findAllDiseaseTagExceptDiseaseType(any())).willReturn(diseaseTagList);

        //when
        DiseaseTagListResponseDto result = diseaseTagService.getDiseaseTagExceptCustomType();

        //then
        assertThat(result.getHeadDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getBronchusDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getChestDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getStomachDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getLimbDiseaseTagList().size()).isEqualTo(1);
        assertThat(result.getSkinDiseaseTagList().size()).isEqualTo(1);

    }

    private List<DiseaseTagResponseDto> createDiseaseTagList() {
        List<DiseaseTagResponseDto> diseaseTagResponseDtoList = new ArrayList<>();
        diseaseTagResponseDtoList.addAll(List.of(
                givenDiseaseTagQueryDto(DiseaseType.HEAD,"두통"),
                givenDiseaseTagQueryDto(DiseaseType.BRONCHUS,"코막힘"),
                givenDiseaseTagQueryDto(DiseaseType.CHEST,"가슴 통증"),
                givenDiseaseTagQueryDto(DiseaseType.STOMACH,"공복감"),
                givenDiseaseTagQueryDto(DiseaseType.LIMB,"관절통"),
                givenDiseaseTagQueryDto(DiseaseType.SKIN,"여드름")
        ));
        return diseaseTagResponseDtoList;
    }
}
