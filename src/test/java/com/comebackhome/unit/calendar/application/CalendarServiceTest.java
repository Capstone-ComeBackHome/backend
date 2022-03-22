package com.comebackhome.unit.calendar.application;

import com.comebackhome.calendar.application.CalendarService;
import com.comebackhome.calendar.application.dto.ScheduleSaveRequestDto;
import com.comebackhome.calendar.domain.repository.DiseaseTagRepository;
import com.comebackhome.calendar.domain.repository.ScheduleDiseaseTagRepository;
import com.comebackhome.calendar.domain.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.comebackhome.calendar.domain.DiseaseType.CUSTOM;
import static com.comebackhome.calendar.domain.DiseaseType.HEAD;
import static com.comebackhome.support.helper.CalendarGivenHelper.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class CalendarServiceTest {

    @InjectMocks CalendarService calendarService;
    @Mock ScheduleRepository scheduleRepository;
    @Mock ScheduleDiseaseTagRepository scheduleDiseaseTagRepository;
    @Mock DiseaseTagRepository diseaseTagRepository;


    @Test
    void CustomType_없는_스케줄_저장하기() throws Exception{
        //given
        ScheduleSaveRequestDto scheduleSaveRequestDto = givenScheduleSaveRequest().toScheduleSaveRequestDto(1L);
        scheduleSaveRequestDto.setDiseaseTagRequestDtoList(List.of(givenDiseaseTagRequestDto(HEAD,"두통")));

        //when
        calendarService.saveMySchedule(scheduleSaveRequestDto);

        //then
        then(scheduleRepository).should().save(any());
        then(diseaseTagRepository).should().findDiseaseTagIdListByDiseaseTagNameList(any());
        then(diseaseTagRepository).should(never()).findDiseaseTagListByDiseaseTagNameList(any());
        then(diseaseTagRepository).should(never()).saveAll(any());
        then(scheduleDiseaseTagRepository).should().saveAll(any());
    }

    @Test
    void 이미_존재하는_Custom_Type_diseaseTag로_스케줄_저장하기() throws Exception{
        //given
        ScheduleSaveRequestDto scheduleSaveRequestDto = givenScheduleSaveRequestDto(1L);
        given(scheduleRepository.save(any())).willReturn(1L);
        given(diseaseTagRepository.findDiseaseTagListByDiseaseTagNameList(any()))
                .willReturn(List.of(givenDiseaseTag(CUSTOM,"교통사고")));

        //when
        calendarService.saveMySchedule(scheduleSaveRequestDto);

        //then
        then(scheduleDiseaseTagRepository).should().saveAll(any());
    }

    @Test
    void 존재하지_않는_customType_diseaseTag_스케줄_저장하기() throws Exception{
        //given
        ScheduleSaveRequestDto scheduleSaveRequestDto = givenScheduleSaveRequestDto(1L);
        given(scheduleRepository.save(any())).willReturn(1L);

        //when
        calendarService.saveMySchedule(scheduleSaveRequestDto);

        //then
        then(scheduleDiseaseTagRepository).should().saveAll(any());
    }

}
