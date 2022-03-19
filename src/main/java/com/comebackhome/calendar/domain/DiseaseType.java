package com.comebackhome.calendar.domain;

public enum DiseaseType {

    HEAD("머리"),
    BRONCHUS("기관지"),
    CHEST("가슴"),
    STOMACH("배"),
    LIMB("팔다리"),
    SKIN("피부"),
    CUSTOM("사용자지정");

    private String description;

    DiseaseType(String description) {
        this.description = description;
    }
}
