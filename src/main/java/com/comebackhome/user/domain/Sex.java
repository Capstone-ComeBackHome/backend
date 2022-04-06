package com.comebackhome.user.domain;

public enum Sex {
    MAN("남자"),
    WOMAN("여자");

    private String description;

    Sex(String description) {
        this.description = description;
    }
}
