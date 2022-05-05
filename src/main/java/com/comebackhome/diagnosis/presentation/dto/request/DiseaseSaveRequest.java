package com.comebackhome.diagnosis.presentation.dto.request;

import com.comebackhome.diagnosis.domain.disease.service.dto.request.DiseaseSaveRequestDto;
import com.opencsv.bean.CsvBindByPosition;
import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseSaveRequest {

    @CsvBindByPosition(position = 0,required = true)
    private String name;

    @CsvBindByPosition(position = 1,required = true)
    private String definition;

    @CsvBindByPosition(position = 2,required = true)
    private String recommendDepartment;

    @CsvBindByPosition(position = 3,required = true)
    private String symptom;

    @CsvBindByPosition(position = 4,required = true)
    private String cause;

    @CsvBindByPosition(position = 5,required = true)
    private String hospitalCare;

    public DiseaseSaveRequestDto toDiseaseSaveRequestDto(){
        return DiseaseSaveRequestDto.builder()
                .name(name)
                .definition(definition)
                .recommendDepartment(recommendDepartment)
                .symptom(symptom)
                .cause(cause)
                .hospitalCare(hospitalCare)
                .build();
    }
}
