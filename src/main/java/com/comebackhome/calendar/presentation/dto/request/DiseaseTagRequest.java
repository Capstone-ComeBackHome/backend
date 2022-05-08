package com.comebackhome.calendar.presentation.dto.request;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.schedule.service.dto.request.DiseaseTagRequestDto;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseTagRequest {

    @NotBlank(message = "diseaseType 값이 들어오지 않았습니다.")
    private DiseaseType diseaseType;

    @NotBlank(message = "name 값이 들어오지 않았습니다.")
    private String name;

    public DiseaseTagRequestDto toDiseaseTagRequestDto(){
        return DiseaseTagRequestDto.builder()
                .diseaseType(diseaseType)
                .name(name)
                .build();

    }

}
