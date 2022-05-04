package com.comebackhome.support.helper;

import com.comebackhome.calendar.domain.PainType;
import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.domain.diseasetag.DiseaseTag;
import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.diseasetag.ScheduleDiseaseTag;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagListResponseDto;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagResponseDto;
import com.comebackhome.calendar.domain.service.dto.request.DiseaseTagRequestDto;
import com.comebackhome.calendar.domain.service.dto.request.ScheduleModifyRequestDto;
import com.comebackhome.calendar.domain.service.dto.request.ScheduleSaveRequestDto;
import com.comebackhome.calendar.domain.service.dto.response.ScheduleResponseDto;
import com.comebackhome.calendar.domain.service.dto.response.SimpleScheduleResponseDto;
import com.comebackhome.calendar.presentation.dto.request.DiseaseTagRequest;
import com.comebackhome.calendar.presentation.dto.request.ScheduleModifyRequest;
import com.comebackhome.calendar.presentation.dto.request.ScheduleSaveRequest;
import com.comebackhome.calendar.presentation.dto.response.SimpleScheduleResponseList;
import com.comebackhome.user.domain.User;

import java.time.LocalDate;
import java.util.List;

import static com.comebackhome.calendar.domain.diseasetag.DiseaseType.*;

public class CalendarGivenHelper {

    public static DiseaseTag givenDiseaseTag(DiseaseType diseaseType, String name){
        return DiseaseTag.builder()
                .diseaseType(diseaseType)
                .name(name)
                .build();
    }

    public static DiseaseTagResponseDto givenDiseaseTagQueryDto(DiseaseType diseaseType, String name){
        return DiseaseTagResponseDto.builder()
                .diseaseType(diseaseType)
                .name(name)
                .build();
    }

    public static DiseaseTagListResponseDto givenDiseaseTagListResponseDto(){
        return DiseaseTagListResponseDto.builder()
                .headDiseaseTagList(List.of(DiseaseTagResponseDto.builder().diseaseType(HEAD).name("두통").build()))
                .bronchusDiseaseTagList(List.of(DiseaseTagResponseDto.builder().diseaseType(BRONCHUS).name("코막힘").build()))
                .chestDiseaseTagList(List.of(DiseaseTagResponseDto.builder().diseaseType(CHEST).name("가슴 통증").build()))
                .stomachDiseaseTagList(List.of(DiseaseTagResponseDto.builder().diseaseType(STOMACH).name("공복감").build()))
                .limbDiseaseTagList(List.of(DiseaseTagResponseDto.builder().diseaseType(LIMB).name("관절통").build()))
                .skinDiseaseTagList(List.of(DiseaseTagResponseDto.builder().diseaseType(SKIN).name("여드름").build()))
                .build();
    }

    public static ScheduleSaveRequestDto givenScheduleSaveRequestDto(Long userId){
        return givenScheduleSaveRequest().toScheduleSaveRequestDto(userId);
    }

    public static ScheduleSaveRequest givenScheduleSaveRequest(){
        return ScheduleSaveRequest.builder()
                .diseaseTagRequestList(List.of(
                        givenDiseaseTagRequest(HEAD,"두통"),
                        givenDiseaseTagRequest(SKIN,"여드름"),
                        givenDiseaseTagRequest(CUSTOM,"교통사고")
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
                .scheduleDiseaseTagList(List.of(ScheduleDiseaseTag.of(1L,1L)))
                .dailyNote("오늘은 조금 괜찮아요.")
                .painType(PainType.GOOD)
                .build();
    }

    public static SimpleScheduleResponseList givenSimpleScheduleResponseList(List<SimpleScheduleResponseDto> simpleScheduleResponseDtoList) {
        return SimpleScheduleResponseList.from(simpleScheduleResponseDtoList);
    }

    public static SimpleScheduleResponseDto givenSimpleScheduleResponseDto(Long scheduleId, LocalDate localDate, int diseaseTagCount) {
        return SimpleScheduleResponseDto.builder()
                .scheduleId(scheduleId)
                .localDate(localDate)
                .diseaseTagCount(diseaseTagCount)
                .build();
    }

    public static ScheduleResponseDto givenScheduleResponseDto() {
        return ScheduleResponseDto.builder()
                .scheduleId(1L)
                .localDate(LocalDate.now())
                .diseaseTagResponseDtoList(List.of(
                        givenDiseaseTagResponseDto(HEAD,"두통"),
                        givenDiseaseTagResponseDto(CUSTOM,"디스크")
                ))
                .dailyNote("오늘은 조금 괜찮아요.")
                .painType(PainType.GOOD)
                .build();
    }

    public static DiseaseTagResponseDto givenDiseaseTagResponseDto(DiseaseType diseaseType, String name){
        return DiseaseTagResponseDto.builder()
                .diseaseType(diseaseType)
                .name(name)
                .build();
    }

    public static ScheduleModifyRequest givenScheduleModifyRequest(){
        return ScheduleModifyRequest.builder()
                .dailyNote("오늘은 조금 아프네요.")
                .painType(PainType.BAD)
                .diseaseTagRequestList(List.of(
                        givenDiseaseTagRequest(HEAD,"두통"),
                        givenDiseaseTagRequest(CUSTOM,"디스크")
                ))
                .build();
    }

    public static ScheduleModifyRequestDto givenScheduleModifyRequestDto(){
        return givenScheduleModifyRequest().toScheduleModifyRequestDto();
    }

}
