package com.comebackhome.unit.calendar.application;

import com.comebackhome.calendar.application.CalendarService;
import com.comebackhome.calendar.application.dto.ScheduleSaveRequestDto;
import com.comebackhome.calendar.domain.repository.DiseaseTagRepository;
import com.comebackhome.calendar.domain.repository.ScheduleDiseaseTagRepository;
import com.comebackhome.calendar.domain.repository.ScheduleRepository;
import com.comebackhome.common.exception.schedule.ScheduleIsNotMineException;
import com.comebackhome.common.exception.schedule.ScheduleNotFoundException;
import com.comebackhome.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.comebackhome.calendar.domain.DiseaseType.CUSTOM;
import static com.comebackhome.calendar.domain.DiseaseType.HEAD;
import static com.comebackhome.support.helper.CalendarGivenHelper.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void scheduleId로_스케줄_삭제() throws Exception{
        //given
        given(scheduleRepository.findById(any()))
                .willReturn(Optional.of(givenSchedule(User.builder().id(1L).build())));

        //when
        calendarService.deleteSchedule(any(),1L);

        //then
        then(scheduleDiseaseTagRepository).should().deleteByScheduleId(any());
        then(scheduleRepository).should().deleteById(any());
    }

    @Test
    void 존재하지_않는_스케줄의_scheduleId() throws Exception{
        //given
        given(scheduleRepository.findById(any()))
                .willReturn(Optional.empty());

        //when then
        assertThatThrownBy(
                () -> calendarService.deleteSchedule(1L,1L))
                .isInstanceOf(ScheduleNotFoundException.class);
    }

    @Test
    void 내_schedule이_아닌_경우_삭제_실패() throws Exception{
        //given
        given(scheduleRepository.findById(any()))
                .willReturn(Optional.of(givenSchedule(User.builder().id(1L).build())));

        //when then
        assertThatThrownBy(
                () -> calendarService.deleteSchedule(any(),2L))
                .isInstanceOf(ScheduleIsNotMineException.class);
        ;

    }

}
