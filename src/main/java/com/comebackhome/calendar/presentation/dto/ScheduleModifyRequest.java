package com.comebackhome.calendar.presentation.dto;

import com.comebackhome.calendar.application.dto.ScheduleModifyRequestDto;
import com.comebackhome.calendar.domain.PainType;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleModifyRequest {

    @NotNull(message = "증상은 1개 이상 5개 이하여야 합니다.")
    @Size(min = 1, max = 5, message = "증상은 1개 이상 5개 이하여야 합니다.")
    private List<DiseaseTagRequest> diseaseTagRequestList;

    private String dailyNote;

    private PainType painType;

    public ScheduleModifyRequestDto toScheduleModifyRequestDto(){
        return ScheduleModifyRequestDto.builder()
                .diseaseTagRequestDtoList(diseaseTagRequestList.parallelStream()
                        .map(DiseaseTagRequest::toDiseaseTagRequestDto)
                        .collect(Collectors.toList()))
                .dailyNote(dailyNote)
                .painType(painType)
                .build();
    }

}
