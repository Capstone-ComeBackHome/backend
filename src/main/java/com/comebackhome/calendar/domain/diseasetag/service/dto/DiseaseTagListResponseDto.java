package com.comebackhome.calendar.domain.diseasetag.service.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseTagListResponseDto {

    private List<DiseaseTagResponseDto> headDiseaseTagList;

    private List<DiseaseTagResponseDto> bronchusDiseaseTagList;

    private List<DiseaseTagResponseDto> chestDiseaseTagList;

    private List<DiseaseTagResponseDto> stomachDiseaseTagList;

    private List<DiseaseTagResponseDto> limbDiseaseTagList;

    private List<DiseaseTagResponseDto> skinDiseaseTagList;

}
