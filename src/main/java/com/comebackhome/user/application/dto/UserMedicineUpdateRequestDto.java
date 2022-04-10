package com.comebackhome.user.application.dto;

import com.comebackhome.user.domain.dto.UserMedicineUpdateDto;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMedicineUpdateRequestDto {

    private String history;

    private String FamilyHistory;

    private String drugHistory;

    private String socialHistory;

    private String traumaHistory;

    public UserMedicineUpdateDto toUserMedicineUpdateDto(){
        return UserMedicineUpdateDto.builder()
                .history(history)
                .FamilyHistory(FamilyHistory)
                .drugHistory(drugHistory)
                .socialHistory(socialHistory)
                .traumaHistory(traumaHistory)
                .build();
    }


}
