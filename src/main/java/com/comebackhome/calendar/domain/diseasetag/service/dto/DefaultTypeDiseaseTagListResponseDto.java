package com.comebackhome.calendar.domain.diseasetag.service.dto;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultTypeDiseaseTagListResponseDto {

    private DiseaseTagListResponseDto head;

    private DiseaseTagListResponseDto bronchus;

    private DiseaseTagListResponseDto chest;

    private DiseaseTagListResponseDto stomach;

    private DiseaseTagListResponseDto limb;

    private DiseaseTagListResponseDto skin;

}
