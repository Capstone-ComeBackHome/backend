package com.comebackhome.diagnosis.domain.disease.service.dto.request;

import com.comebackhome.diagnosis.domain.disease.Disease;
import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseSaveRequestDto {

    private String name;

    private String definition;

    private String recommendDepartment;

    private String symptom;

    private String cause;

    private String hospitalCare;

    public Disease toEntity(){
        return Disease.builder()
                .name(name)
                .definition(definition)
                .recommendDepartment(recommendDepartment)
                .symptom(symptom)
                .cause(cause)
                .hospitalCare(hospitalCare)
                .build();
    }
}
