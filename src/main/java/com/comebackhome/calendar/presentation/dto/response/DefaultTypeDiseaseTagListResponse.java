package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.application.dto.response.DiseaseTagResponseDto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultTypeDiseaseTagListResponse {

    private List<DiseaseTagResponse> headDiseaseTagList;

    private List<DiseaseTagResponse> bronchusDiseaseTagList;

    private List<DiseaseTagResponse> chestDiseaseTagList;

    private List<DiseaseTagResponse> stomachDiseaseTagList;

    private List<DiseaseTagResponse> limbDiseaseTagList;

    private List<DiseaseTagResponse> skinDiseaseTagList;

    public static DefaultTypeDiseaseTagListResponse from(DiseaseTagResponseDto diseaseTagResponseDto){
        return DefaultTypeDiseaseTagListResponse.builder()
                .headDiseaseTagList(diseaseTagResponseDto.getHeadDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .bronchusDiseaseTagList(diseaseTagResponseDto.getBronchusDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .chestDiseaseTagList(diseaseTagResponseDto.getChestDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .stomachDiseaseTagList(diseaseTagResponseDto.getStomachDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .limbDiseaseTagList(diseaseTagResponseDto.getLimbDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .skinDiseaseTagList(diseaseTagResponseDto.getSkinDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .build();
    }

}
