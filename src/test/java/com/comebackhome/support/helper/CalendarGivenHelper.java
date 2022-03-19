package com.comebackhome.support.helper;

import com.comebackhome.calendar.application.dto.DiseaseTagDto;
import com.comebackhome.calendar.application.dto.DiseaseTagResponseDto;
import com.comebackhome.calendar.domain.DiseaseTag;
import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.dto.DiseaseTagQueryDto;

import java.util.List;

public class CalendarGivenHelper {

    public static DiseaseTag givenDiseaseTag(DiseaseType diseaseType, String name){
        return DiseaseTag.builder()
                .diseaseType(diseaseType)
                .name(name)
                .build();
    }

    public static DiseaseTagQueryDto givenDiseaseTagQueryDto(DiseaseType diseaseType, String name){
        return DiseaseTagQueryDto.builder()
                .diseaseType(diseaseType)
                .name(name)
                .build();
    }

    public static DiseaseTagResponseDto givenDiseaseTagResponseDto(){
        return DiseaseTagResponseDto.builder()
                .headDiseaseTagList(List.of(DiseaseTagDto.builder().diseaseType(DiseaseType.HEAD).name("두통").build()))
                .bronchusDiseaseTagList(List.of(DiseaseTagDto.builder().diseaseType(DiseaseType.BRONCHUS).name("코막힘").build()))
                .chestDiseaseTagList(List.of(DiseaseTagDto.builder().diseaseType(DiseaseType.CHEST).name("가슴 통증").build()))
                .stomachDiseaseTagList(List.of(DiseaseTagDto.builder().diseaseType(DiseaseType.STOMACH).name("공복감").build()))
                .limbDiseaseTagList(List.of(DiseaseTagDto.builder().diseaseType(DiseaseType.LIMB).name("관절통").build()))
                .skinDiseaseTagList(List.of(DiseaseTagDto.builder().diseaseType(DiseaseType.SKIN).name("여드름").build()))
                .build();

    }


}
