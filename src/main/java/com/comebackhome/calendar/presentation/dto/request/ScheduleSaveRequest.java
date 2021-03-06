package com.comebackhome.calendar.presentation.dto.request;

import com.comebackhome.calendar.domain.schedule.PainType;
import com.comebackhome.calendar.domain.schedule.service.dto.request.ScheduleSaveRequestDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleSaveRequest {

    @NotNull(message = "증상은 1개 이상 5개 이하여야 합니다.")
    @Size(min = 1, max = 5, message = "증상은 1개 이상 5개 이하여야 합니다.")
    private List<DiseaseTagRequest> diseaseTagRequestList;

    private String dailyNote;

    @NotNull(message = "아픔 정도는 핋수값입니다.")
    private PainType painType;

    @NotNull(message = "스케줄 날짜를 'yyyy-MM-dd' 형식으로 입력해주세요.")
    private LocalDate scheduleDate;

    public ScheduleSaveRequestDto toScheduleSaveRequestDto (Long userId){
        return ScheduleSaveRequestDto.builder()
                .diseaseTagRequestDtoList(diseaseTagRequestList.parallelStream()
                        .map(DiseaseTagRequest::toDiseaseTagRequestDto)
                        .collect(Collectors.toList()))
                .dailyNote(dailyNote)
                .painType(painType)
                .scheduleDate(scheduleDate)
                .userId(userId)
                .build();
    }
}
