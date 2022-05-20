package com.comebackhome.calendar.domain.schedule;

import lombok.Getter;

@Getter
public enum PainType {
    WORST("매우 아픔",5),
    BAD("더 아픔",4),
    NORMAL("보통 아픔",3),
    BETTER("괜찮아짐",2),
    GOOD("매우 괜찮아짐",1)
    ;

    private String description;
    private int num;

    PainType(String description, int num) {
        this.description = description;
        this.num = num;
    }
}
