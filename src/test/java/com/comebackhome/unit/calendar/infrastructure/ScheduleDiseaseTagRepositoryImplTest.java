package com.comebackhome.unit.calendar.infrastructure;

import com.comebackhome.calendar.domain.DiseaseTag;
import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.domain.ScheduleDiseaseTag;
import com.comebackhome.calendar.infrastructure.repository.DiseaseTagJpaRepository;
import com.comebackhome.calendar.infrastructure.repository.ScheduleDiseaseTagJpaRepository;
import com.comebackhome.calendar.infrastructure.repository.ScheduleDiseaseTagRepositoryImpl;
import com.comebackhome.calendar.infrastructure.repository.ScheduleJpaRepository;
import com.comebackhome.support.JpaRepositoryTest;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.infrastructure.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.comebackhome.calendar.domain.DiseaseType.HEAD;
import static com.comebackhome.calendar.domain.DiseaseType.SKIN;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTag;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenSchedule;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleDiseaseTagRepositoryImplTest extends JpaRepositoryTest {

    @Autowired ScheduleDiseaseTagRepositoryImpl scheduleDiseaseTagRepository;
    @Autowired ScheduleDiseaseTagJpaRepository scheduleDiseaseTagJpaRepository;
    @Autowired ScheduleJpaRepository scheduleJpaRepository;
    @Autowired DiseaseTagJpaRepository diseaseTagJpaRepository;
    @Autowired UserJpaRepository userJpaRepository;

    @Test
    void scheduleDiseaseTagList_한번에_save() throws Exception{
        //given
        List<ScheduleDiseaseTag> scheduleDiseaseTagList = getScheduleDiseaseTagList();

        //when
        scheduleDiseaseTagRepository.saveAll(scheduleDiseaseTagList);

        //then
        List<ScheduleDiseaseTag> result = scheduleDiseaseTagJpaRepository.findAll();
        Set<Long> resultIdSet = result.stream().map(scheduleDiseaseTag -> scheduleDiseaseTag.getSchedule().getId())
                .collect(Collectors.toSet());

        assertThat(result.size()).isEqualTo(2);
        assertThat(resultIdSet.size()).isEqualTo(1);
    }

    private List<ScheduleDiseaseTag> getScheduleDiseaseTagList() {
        User user = userJpaRepository.save(givenUser());
        Schedule schedule = scheduleJpaRepository.save(givenSchedule(user));
        DiseaseTag diseaseTag1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통"));
        DiseaseTag diseaseTag2 = diseaseTagJpaRepository.save(givenDiseaseTag(SKIN, "여드름"));

        return List.of(ScheduleDiseaseTag.of(schedule.getId(), diseaseTag1.getId()),
                ScheduleDiseaseTag.of(schedule.getId(), diseaseTag2.getId()));
    }

    @Test
    void scheduleId값으로_scheduleDiseaseTag_삭제() throws Exception{
        //given
        Long scheduleId = saveSchedule();

        //when
        scheduleDiseaseTagRepository.deleteByScheduleId(scheduleId);

        //then
        List<ScheduleDiseaseTag> result = scheduleDiseaseTagJpaRepository.findAll();
        assertThat(result).isEmpty();
    }

    private Long saveSchedule() {
        User user = userJpaRepository.save(givenUser());
        DiseaseTag diseaseTag1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통"));
        DiseaseTag diseaseTag2 = diseaseTagJpaRepository.save(givenDiseaseTag(SKIN, "여드름"));

        Schedule schedule = scheduleJpaRepository.save(givenSchedule(user));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(schedule.getId(),diseaseTag1.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(schedule.getId(),diseaseTag2.getId()));
        return schedule.getId();
    }


    @Test
    void scheduleDiseaseTagIdList로_scheduleDiseaseTag_삭제() throws Exception{
        //given
        User user = userJpaRepository.save(givenUser());
        DiseaseTag diseaseTag1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통"));
        DiseaseTag diseaseTag2 = diseaseTagJpaRepository.save(givenDiseaseTag(SKIN, "여드름"));

        Schedule schedule = scheduleJpaRepository.save(givenSchedule(user));
        Long scheduleDiseaseTagId = scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(schedule.getId(), diseaseTag1.getId())).getId();
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(schedule.getId(),diseaseTag2.getId()));

        //when
        scheduleDiseaseTagRepository.deleteByIdList(List.of(scheduleDiseaseTagId));

        //then
        List<ScheduleDiseaseTag> result = scheduleDiseaseTagJpaRepository.findAll();
        assertThat(result.size()).isEqualTo(1);
    }



}
