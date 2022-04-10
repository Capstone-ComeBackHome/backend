package com.comebackhome.user.presentation.dto.response;

import com.comebackhome.user.domain.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMedicineResponse {

    private String history;

    private String FamilyHistory;

    private String drugHistory;

    private String socialHistory;

    private String traumaHistory;


    public static UserMedicineResponse from(User user){
        return UserMedicineResponse.builder()
                .history(user.getHistory())
                .FamilyHistory(user.getFamilyHistory())
                .drugHistory(user.getDrugHistory())
                .socialHistory(user.getSocialHistory())
                .traumaHistory(user.getTraumaHistory())
                .build();
    }
}
