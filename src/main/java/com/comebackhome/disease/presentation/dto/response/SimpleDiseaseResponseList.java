package com.comebackhome.disease.presentation.dto.response;

import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleDiseaseResponseList {

    private List<SimpleDiseaseResponse> simpleDiseaseList;

    public static SimpleDiseaseResponseList from(List<SimpleDiseaseResponseDto> simpleDiseaseResponseDtoList){
        return SimpleDiseaseResponseList.builder()
                .simpleDiseaseList(
                        simpleDiseaseResponseDtoList.stream()
                        .map(SimpleDiseaseResponse::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
