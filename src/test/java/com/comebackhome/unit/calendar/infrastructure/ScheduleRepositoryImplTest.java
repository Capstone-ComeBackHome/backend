package com.comebackhome.unit.calendar.infrastructure;

import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.infrastructure.repository.ScheduleJpaRepository;
import com.comebackhome.calendar.infrastructure.repository.ScheduleRepositoryImpl;
import com.comebackhome.support.JpaRepositoryTest;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.infrastructure.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenSchedule;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleRepositoryImplTest extends JpaRepositoryTest {

    @Autowired ScheduleRepositoryImpl scheduleRepository;
    @Autowired UserJpaRepository userJpaRepository;
    @Autowired ScheduleJpaRepository scheduleJpaRepository;

    @Test
    void schedule_저장() throws Exception{
        //given
        User user = userJpaRepository.save(givenUser());

        //when
        scheduleRepository.save(givenSchedule(user));

        //then
        List<Schedule> result = scheduleJpaRepository.findAll();

        assertThat(result.size()).isEqualTo(1);
    }
}
