package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagListResponseDto;
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

    public static DefaultTypeDiseaseTagListResponse from(DiseaseTagListResponseDto diseaseTagListResponseDto){
        return DefaultTypeDiseaseTagListResponse.builder()
                .headDiseaseTagList(diseaseTagListResponseDto.getHeadDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .bronchusDiseaseTagList(diseaseTagListResponseDto.getBronchusDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .chestDiseaseTagList(diseaseTagListResponseDto.getChestDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .stomachDiseaseTagList(diseaseTagListResponseDto.getStomachDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .limbDiseaseTagList(diseaseTagListResponseDto.getLimbDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .skinDiseaseTagList(diseaseTagListResponseDto.getSkinDiseaseTagList().parallelStream().map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .build();
    }

}
