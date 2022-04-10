package com.comebackhome.user.presentation.dto.response;

import com.comebackhome.user.domain.AuthProvider;
import com.comebackhome.user.domain.User;
import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSimpleInfoResponse {

    private String email;

    private String name;

    private String picture;

    private AuthProvider authProvider;

    public static UserSimpleInfoResponse from(User user){
        return UserSimpleInfoResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .picture(user.getPicture())
                .authProvider(user.getAuthProvider())
                .build();
    }
}
