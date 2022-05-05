package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.diseasetag.service.dto.DefaultTypeDiseaseTagListResponseDto;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultTypeDiseaseTagListResponse {

    private DiseaseTagListResponse head;

    private DiseaseTagListResponse bronchus;

    private DiseaseTagListResponse chest;

    private DiseaseTagListResponse stomach;

    private DiseaseTagListResponse limb;

    private DiseaseTagListResponse skin;

    public static DefaultTypeDiseaseTagListResponse from(DefaultTypeDiseaseTagListResponseDto defaultTypeDiseaseTagListResponseDto){
        return DefaultTypeDiseaseTagListResponse.builder()
                .head(DiseaseTagListResponse.from(defaultTypeDiseaseTagListResponseDto.getHead()))
                .bronchus(DiseaseTagListResponse.from(defaultTypeDiseaseTagListResponseDto.getBronchus()))
                .chest(DiseaseTagListResponse.from(defaultTypeDiseaseTagListResponseDto.getChest()))
                .stomach(DiseaseTagListResponse.from(defaultTypeDiseaseTagListResponseDto.getStomach()))
                .limb(DiseaseTagListResponse.from(defaultTypeDiseaseTagListResponseDto.getLimb()))
                .skin(DiseaseTagListResponse.from(defaultTypeDiseaseTagListResponseDto.getSkin()))
                .build();
    }

}
