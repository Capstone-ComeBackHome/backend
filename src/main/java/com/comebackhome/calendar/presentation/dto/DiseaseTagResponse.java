package com.comebackhome.calendar.presentation.dto;

import com.comebackhome.calendar.application.dto.DiseaseTagDto;
import com.comebackhome.calendar.application.dto.DiseaseTagResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseTagResponse {

    private List<DiseaseTagDto> headDiseaseTagList;

    private List<DiseaseTagDto> bronchusDiseaseTagList;

    private List<DiseaseTagDto> chestDiseaseTagList;

    private List<DiseaseTagDto> stomachDiseaseTagList;

    private List<DiseaseTagDto> limbDiseaseTagList;

    private List<DiseaseTagDto> skinDiseaseTagList;

    public static DiseaseTagResponse from(DiseaseTagResponseDto diseaseTagResponseDto){
        return DiseaseTagResponse.builder()
                .headDiseaseTagList(diseaseTagResponseDto.getHeadDiseaseTagList())
                .bronchusDiseaseTagList(diseaseTagResponseDto.getBronchusDiseaseTagList())
                .chestDiseaseTagList(diseaseTagResponseDto.getChestDiseaseTagList())
                .stomachDiseaseTagList(diseaseTagResponseDto.getStomachDiseaseTagList())
                .limbDiseaseTagList(diseaseTagResponseDto.getLimbDiseaseTagList())
                .skinDiseaseTagList(diseaseTagResponseDto.getSkinDiseaseTagList())
                .build();
    }

}
