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
        User user = userJpaRepository.save(givenUser());
        Schedule schedule = scheduleJpaRepository.save(givenSchedule(user));
        DiseaseTag diseaseTag1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통"));
        DiseaseTag diseaseTag2 = diseaseTagJpaRepository.save(givenDiseaseTag(SKIN, "여드름"));

        List<ScheduleDiseaseTag> scheduleDiseaseTagList = List.of(ScheduleDiseaseTag.of(schedule.getId(), diseaseTag1.getId()),
                ScheduleDiseaseTag.of(schedule.getId(), diseaseTag2.getId()));

        //when
        scheduleDiseaseTagRepository.saveAll(scheduleDiseaseTagList);

        //then
        List<ScheduleDiseaseTag> result = scheduleDiseaseTagJpaRepository.findAll();
        Set<Long> resultIdSet = result.stream().map(scheduleDiseaseTag -> scheduleDiseaseTag.getSchedule().getId())
                .collect(Collectors.toSet());

        assertThat(result.size()).isEqualTo(2);
        assertThat(resultIdSet.size()).isEqualTo(1);
    }
}
