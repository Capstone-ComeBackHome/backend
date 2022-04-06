package com.comebackhome.user.domain;

import lombok.Getter;

@Getter
public enum AuthProvider {
    google("구글"),
    naver("네이버"),
    kakao("카카오");

    private String description;

    AuthProvider(String description) {
        this.description = description;
    }
}
