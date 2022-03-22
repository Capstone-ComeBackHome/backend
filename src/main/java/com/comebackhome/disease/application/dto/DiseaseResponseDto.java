package com.comebackhome.disease.application.dto;

import com.comebackhome.disease.domain.Disease;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseResponseDto {

    private String name;

    private String definition;

    private String recommendDepartment;

    private String symptom;

    private String cause;

    private String hospitalCare;


    public static DiseaseResponseDto from(Disease disease) {
        return DiseaseResponseDto.builder()
                .name(disease.getName())
                .definition(disease.getDefinition())
                .recommendDepartment(disease.getRecommendDepartment())
                .symptom(disease.getSymptom())
                .cause(disease.getCause())
                .hospitalCare(disease.getHospitalCare())
               .build();
    }

}
