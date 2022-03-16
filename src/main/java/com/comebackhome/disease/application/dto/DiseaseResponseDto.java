package com.comebackhome.disease.application.dto;

import com.comebackhome.disease.domain.dto.DiseaseQueryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseResponseDto {

    private String name;

    private String definition;

    private String recommendDepartment;

    private String symptom;

    private List<String> causeList;

    private String hospitalCare;

    private List<String> homeCareList;

    private String complications;

    public static DiseaseResponseDto from(DiseaseQueryDto diseaseQueryDto) {
        return DiseaseResponseDto.builder()
                .name(diseaseQueryDto.getName())
                .definition(diseaseQueryDto.getDefinition())
                .recommendDepartment(diseaseQueryDto.getRecommendDepartment())
                .symptom(diseaseQueryDto.getSymptom())
                .causeList(diseaseQueryDto.getCauseList())
                .hospitalCare(diseaseQueryDto.getHospitalCare())
                .homeCareList(diseaseQueryDto.getHomeCareList())
                .complications(diseaseQueryDto.getComplications())
               .build();
    }

}
