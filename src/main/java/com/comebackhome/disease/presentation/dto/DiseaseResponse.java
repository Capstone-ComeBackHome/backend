package com.comebackhome.disease.presentation.dto;

import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseResponse {

    private String name;

    private String definition;

    private String recommendDepartment;

    private String symptom;

    private List<String> causeList;

    private String hospitalCare;

    private List<String> homeCareList;

    private String complications;

    public static DiseaseResponse from(DiseaseResponseDto diseaseResponseDto) {
        return DiseaseResponse.builder()
                .name(diseaseResponseDto.getName())
                .definition(diseaseResponseDto.getDefinition())
                .recommendDepartment(diseaseResponseDto.getRecommendDepartment())
                .symptom(diseaseResponseDto.getSymptom())
                .causeList(diseaseResponseDto.getCauseList())
                .hospitalCare(diseaseResponseDto.getHospitalCare())
                .homeCareList(diseaseResponseDto.getHomeCareList())
                .complications(diseaseResponseDto.getComplications())
                .build();
    }
}
