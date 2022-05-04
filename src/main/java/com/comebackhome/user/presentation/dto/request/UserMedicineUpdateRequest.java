package com.comebackhome.user.presentation.dto.request;


import com.comebackhome.user.domain.service.dto.UserMedicineUpdateRequestDto;
import lombok.*;

import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMedicineUpdateRequest {

    @Size(max = 50,message = "50자 이내로 입력하세요.")
    private String history;

    @Size(max = 50,message = "50자 이내로 입력하세요.")
    private String FamilyHistory;

    @Size(max = 50,message = "50자 이내로 입력하세요.")
    private String drugHistory;

    @Size(max = 50,message = "50자 이내로 입력하세요.")
    private String socialHistory;

    @Size(max = 50,message = "50자 이내로 입력하세요.")
    private String traumaHistory;


    public UserMedicineUpdateRequestDto toUserMedicineUpdateRequestDto(){
        return UserMedicineUpdateRequestDto.builder()
                .history(history)
                .FamilyHistory(FamilyHistory)
                .drugHistory(drugHistory)
                .socialHistory(socialHistory)
                .traumaHistory(traumaHistory)
                .build();
    }


}
