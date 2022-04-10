package com.comebackhome.user.presentation.dto.request;


import com.comebackhome.user.application.dto.UserMedicineUpdateRequestDto;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMedicineUpdateRequest {

    private String history;

    private String FamilyHistory;

    private String drugHistory;

    private String socialHistory;

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
