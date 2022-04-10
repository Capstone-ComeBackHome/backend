package com.comebackhome.user.presentation.dto.response;

import com.comebackhome.user.domain.Sex;
import com.comebackhome.user.domain.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEssentialResponse {

    private int age;

    private Sex sex;

    private int height;

    private int weight;

    public static UserEssentialResponse from(User user){
        return UserEssentialResponse.builder()
                .age(user.getAge())
                .sex(user.getSex())
                .height(user.getHeight())
                .weight(user.getWeight())
                .build();
    }

}
