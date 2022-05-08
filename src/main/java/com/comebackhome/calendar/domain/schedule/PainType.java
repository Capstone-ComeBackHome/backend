package com.comebackhome.calendar.domain.schedule;

import lombok.Getter;

@Getter
public enum PainType {
    WORST("매우 아픔"),
    BAD("더 아픔"),
    NORMAL("보통 아픔"),
    BETTER("괜찮아짐"),
    GOOD("매우 괜찮아짐")
    ;

    private String description;

    PainType(String description) {
        this.description = description;
    }
}
