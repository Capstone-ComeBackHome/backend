package com.comebackhome.calendar.application.dto.response;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseTagResponseDto {

    private List<DiseaseTagDto> headDiseaseTagList;

    private List<DiseaseTagDto> bronchusDiseaseTagList;

    private List<DiseaseTagDto> chestDiseaseTagList;

    private List<DiseaseTagDto> stomachDiseaseTagList;

    private List<DiseaseTagDto> limbDiseaseTagList;

    private List<DiseaseTagDto> skinDiseaseTagList;

}
