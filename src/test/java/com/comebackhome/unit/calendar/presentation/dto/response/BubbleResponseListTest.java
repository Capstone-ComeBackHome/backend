package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.presentation.dto.response.BubbleResponse;
import com.comebackhome.calendar.presentation.dto.response.BubbleResponseList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BubbleResponseListTest {

    @Test
    void 정적_메서드_from_으로_생성() {
        //given
        List<BubbleResponse> bubbleResponseList = List.of(new BubbleResponse(DiseaseType.CUSTOM,1,1.1),
                new BubbleResponse(DiseaseType.CUSTOM,1,1.1));

        //when
        BubbleResponseList result = BubbleResponseList.from(bubbleResponseList);

        //then
        assertThat(result.getBubbleResponseList().size()).isEqualTo(2);
    }
}
