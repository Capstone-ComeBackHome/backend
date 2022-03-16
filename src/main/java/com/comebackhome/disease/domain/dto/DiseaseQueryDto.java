package com.comebackhome.disease.domain.dto;


import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.infrastructure.repository.dto.CauseQueryDto;
import com.comebackhome.disease.infrastructure.repository.dto.HomeCareQueryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseQueryDto {

    private String name;

    private String definition;

    private String recommendDepartment;

    private String symptom;

    private List<String> causeList;

    private String hospitalCare;

    private List<String> homeCareList;

    private String complications;


    public static DiseaseQueryDto of(Disease disease, List<CauseQueryDto> causeQueryDto, List<HomeCareQueryDto> homeCareQueryDto){
        return DiseaseQueryDto.builder()
                .name(disease.getName())
                .definition(disease.getDefinition())
                .recommendDepartment(disease.getRecommendDepartment())
                .symptom(disease.getSymptom())
                .causeList(causeQueryDto.stream().map(CauseQueryDto::getReason).collect(Collectors.toList()))
                .hospitalCare(disease.getHospitalCare())
                .homeCareList(homeCareQueryDto.stream().map(HomeCareQueryDto::getSolution).collect(Collectors.toList()))
                .complications(disease.getComplications())
                .build();
    }

}
