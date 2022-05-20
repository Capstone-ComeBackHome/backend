package com.comebackhome.calendar.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BubbleResponseList {
    List<BubbleResponse> bubbleResponseList;

    public static BubbleResponseList from(List<BubbleResponse> bubbleResponseList){
        return new BubbleResponseList(bubbleResponseList);
    }
}
