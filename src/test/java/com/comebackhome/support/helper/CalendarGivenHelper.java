package com.comebackhome.support.helper;

import com.comebackhome.calendar.application.dto.DiseaseTagDto;
import com.comebackhome.calendar.application.dto.DiseaseTagRequestDto;
import com.comebackhome.calendar.application.dto.DiseaseTagResponseDto;
import com.comebackhome.calendar.application.dto.ScheduleSaveRequestDto;
import com.comebackhome.calendar.domain.DiseaseTag;
import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.PainType;
import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.domain.dto.DiseaseTagQueryDto;
import com.comebackhome.calendar.presentation.dto.DiseaseTagRequest;
import com.comebackhome.calendar.presentation.dto.ScheduleSaveRequest;
import com.comebackhome.user.domain.User;

import java.time.LocalDate;
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

    public static ScheduleSaveRequestDto givenScheduleSaveRequestDto(Long userId){
        return givenScheduleSaveRequest().toScheduleSaveRequestDto(userId);
    }

    public static ScheduleSaveRequest givenScheduleSaveRequest(){
        return ScheduleSaveRequest.builder()
                .diseaseTagRequestList(List.of(
                        givenDiseaseTagRequest(DiseaseType.HEAD,"두통"),
                        givenDiseaseTagRequest(DiseaseType.SKIN,"여드름"),
                        givenDiseaseTagRequest(DiseaseType.CUSTOM,"교통사고")
                ))
                .dailyNote("오늘은 조금 괜찮아진 것 같네요.")
                .localDate(LocalDate.now())
                .painType(PainType.GOOD)
                .build();
    }

    public static DiseaseTagRequest givenDiseaseTagRequest(DiseaseType diseaseType, String name) {
        return DiseaseTagRequest.builder()
                .diseaseType(diseaseType)
                .name(name)
                .build();
    }

    public static DiseaseTagRequestDto givenDiseaseTagRequestDto(DiseaseType diseaseType, String name) {
        return givenDiseaseTagRequest(diseaseType,name).toDiseaseTagRequestDto();
    }

    public static Schedule givenSchedule(User user) {
        return Schedule.builder()
                .user(user)
                .localDate(LocalDate.now())
                .dailyNote("오늘은 조금 괜찮아요.")
                .painType(PainType.GOOD)
                .build();
    }

}
