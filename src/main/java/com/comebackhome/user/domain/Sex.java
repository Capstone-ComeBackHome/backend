package com.comebackhome.user.domain;

import lombok.Getter;

@Getter
public enum Sex {
    MAN("남자"),
    WOMAN("여자");

    private String description;

    Sex(String description) {
        this.description = description;
    }
}
