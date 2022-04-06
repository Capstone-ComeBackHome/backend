package com.comebackhome.disease.presentation.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiagnosisSaveRequest {

    @Size(min = 3, max = 3,message = "질병명은 정확히 3개가 들어와야 합니다.")
    private List<@NotBlank(message = "질병명이 공백일 수 없습니다.") String> diseaseNameList;
}
