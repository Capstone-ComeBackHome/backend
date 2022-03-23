package com.comebackhome.calendar.infrastructure.repository;

import com.comebackhome.calendar.domain.dto.SimpleScheduleQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

import static com.comebackhome.calendar.domain.QSchedule.schedule;

@Repository
@RequiredArgsConstructor
public class ScheduleQuerydslRepository {

    private final JPAQueryFactory query;

    public List<SimpleScheduleQueryDto> findByYearMonthAndUserId(YearMonth yearMonth, Long userId){
        return query.select(Projections.fields(SimpleScheduleQueryDto.class,
                schedule.id.as("scheduleId"),
                schedule.localDate,
                schedule.scheduleDiseaseTagList.size().as("diseaseTagCount")
        ))
                .from(schedule).distinct()
                .where(schedule.user.id.eq(userId),
                        schedule.localDate.between(yearMonth.atDay(1),yearMonth.atEndOfMonth()))
                .fetch();
    }

}
