package com.comebackhome.diagnosis.presentation.dto.response;

import com.comebackhome.diagnosis.domain.disease.service.dto.response.DiseaseResponseDto;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseResponse {

    private String name;

    private String definition;

    private String recommendDepartment;

    private String symptom;

    private String cause;

    private String hospitalCare;


    public static DiseaseResponse from(DiseaseResponseDto diseaseResponseDto) {
        return DiseaseResponse.builder()
                .name(diseaseResponseDto.getName())
                .definition(diseaseResponseDto.getDefinition())
                .recommendDepartment(diseaseResponseDto.getRecommendDepartment())
                .symptom(diseaseResponseDto.getSymptom())
                .cause(diseaseResponseDto.getCause())
                .hospitalCare(diseaseResponseDto.getHospitalCare())
                .build();
    }
}
