package com.comebackhome.calendar.domain.schedule.service.dto.request;

import com.comebackhome.calendar.domain.schedule.PainType;
import com.comebackhome.calendar.domain.schedule.Schedule;
import com.comebackhome.user.domain.User;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleSaveRequestDto {

    private List<DiseaseTagRequestDto> diseaseTagRequestDtoList;

    private String dailyNote;

    private PainType painType;

    private LocalDate localDate;

    private Long userId;

    public Schedule toEntity(){
        return Schedule.builder()
                .localDate(localDate)
                .dailyNote(dailyNote)
                .painType(painType)
                .user(User.builder().id(userId).build())
                .build();
    }

}
