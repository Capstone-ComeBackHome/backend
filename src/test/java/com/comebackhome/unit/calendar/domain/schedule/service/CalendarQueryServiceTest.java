package com.comebackhome.unit.calendar.domain.schedule.service;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.schedule.Schedule;
import com.comebackhome.calendar.domain.schedule.repository.ScheduleDiseaseTagRepository;
import com.comebackhome.calendar.domain.schedule.repository.ScheduleRepository;
import com.comebackhome.calendar.domain.schedule.service.CalendarQueryService;
import com.comebackhome.calendar.domain.schedule.service.dto.response.BubbleResponseDto;
import com.comebackhome.calendar.domain.schedule.service.dto.response.ScheduleResponseDto;
import com.comebackhome.common.exception.schedule.ScheduleNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenBubbleQueryDtoList;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenSchedule;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CalendarQueryServiceTest {

    @InjectMocks CalendarQueryService calendarQueryService;
    @Mock ScheduleRepository scheduleRepository;
    @Mock ScheduleDiseaseTagRepository scheduleDiseaseTagRepository;

    @Test
    void 특정_월_스케줄_가져오기() throws Exception{
        //given
        List<Schedule> scheduleList = List.of(
                givenSchedule(),
                givenSchedule()
        );

        given(scheduleRepository.findWithScheduleDiseaseTagByYearMonthAndUserId(any(),any()))
                .willReturn(scheduleList);

        //when
        List<ScheduleResponseDto> result = calendarQueryService.getMyMonthSchedule(any(), any());

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getDailyNote()).isEqualTo(scheduleList.get(0).getDailyNote());
        assertThat(result.get(0).getScheduleId()).isEqualTo(scheduleList.get(0).getId());
        assertThat(result.get(0).getLocalDate()).isEqualTo(scheduleList.get(0).getLocalDate());
        assertThat(result.get(0).getPainType()).isEqualTo(scheduleList.get(0).getPainType().name());
        assertThat(result.get(0).getDiseaseTagResponseDtoList().size()).isEqualTo(scheduleList.get(0).getScheduleDiseaseTagList().size());

    }

    @Test
    void 특정_스케줄_가져오기() throws Exception{
        //given
        Schedule schedule = givenSchedule();

        given(scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(any(),any()))
                .willReturn(Optional.of(schedule));

        //when
        ScheduleResponseDto result = calendarQueryService.getMySchedule(any(), any());

        //then
        assertThat(result.getScheduleId()).isEqualTo(schedule.getId());
        assertThat(result.getLocalDate()).isEqualTo(schedule.getLocalDate());
        assertThat(result.getDiseaseTagResponseDtoList().size()).isEqualTo(schedule.getScheduleDiseaseTagList().size());
        assertThat(result.getDailyNote()).isEqualTo(schedule.getDailyNote());
        assertThat(result.getPainType()).isEqualTo(schedule.getPainType().name());
    }

    @Test
    void 존재하지_않는_특정_스케줄_가져오기() throws Exception{
        //given
        given(scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(any(),any()))
                .willReturn(Optional.empty());

        //when then
        assertThatThrownBy(
                () -> calendarQueryService.getMySchedule(any(),any()))
                .isInstanceOf(ScheduleNotFoundException.class);
    }

    @Test
    void bubble_그래프_데이터_조회() {
        //given
        given(scheduleDiseaseTagRepository.findBubbleQueryDtoByUserIdWithinAMonthExceptCustomType(any())).willReturn(givenBubbleQueryDtoList());

        //when
        List<BubbleResponseDto> result = calendarQueryService.getBubbleStatisticData(any());

        //then
        assertThat(result.get(0).getDiseaseType()).isEqualTo(DiseaseType.HEAD);
        assertThat(result.get(0).getCount()).isEqualTo(2);
        assertThat(result.get(0).getPainAverage()).isEqualTo(4.5);

        assertThat(result.get(1).getDiseaseType()).isEqualTo(DiseaseType.BRONCHUS);
        assertThat(result.get(1).getCount()).isEqualTo(0);
        assertThat(result.get(1).getPainAverage()).isEqualTo(0.0);

        assertThat(result.get(2).getDiseaseType()).isEqualTo(DiseaseType.CHEST);
        assertThat(result.get(2).getCount()).isEqualTo(0);
        assertThat(result.get(2).getPainAverage()).isEqualTo(0.0);

        assertThat(result.get(3).getDiseaseType()).isEqualTo(DiseaseType.STOMACH);
        assertThat(result.get(3).getCount()).isEqualTo(0);
        assertThat(result.get(3).getPainAverage()).isEqualTo(0.0);

        assertThat(result.get(4).getDiseaseType()).isEqualTo(DiseaseType.LIMB);
        assertThat(result.get(4).getCount()).isEqualTo(0);
        assertThat(result.get(4).getPainAverage()).isEqualTo(0.0);

        assertThat(result.get(5).getDiseaseType()).isEqualTo(DiseaseType.SKIN);
        assertThat(result.get(5).getCount()).isEqualTo(1);
        assertThat(result.get(5).getPainAverage()).isEqualTo(3.0);

    }
}
