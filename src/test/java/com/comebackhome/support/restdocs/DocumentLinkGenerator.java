package com.comebackhome.support.restdocs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface DocumentLinkGenerator {

    static String generateLinkCode(DocUrl docUrl) {
        return String.format("link:%s.html[%s %s,role=\"popup\"]", docUrl.pageId, docUrl.text, "코드");
    }

    static String generateText(DocUrl docUrl) {
        return String.format("%s %s", docUrl.text, "코드명");
    }

    // 필요한 url 쭉 명시
    @RequiredArgsConstructor
    enum DocUrl {
        DISEASE_TYPE("disease-type","질병 태그 타입"),
        PAIN_TYPE("pain-type","아픈 정도"),
        ;

        private final String pageId;
        @Getter
        private final String text;
    }
}
