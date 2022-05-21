package com.comebackhome.calendar.domain.schedule.service.dto.response;

import com.comebackhome.calendar.domain.schedule.repository.dto.LineQueryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LineResponseDto {

    private List<LineQueryDto> top1;
    private List<LineQueryDto> top2;
    private List<LineQueryDto> top3;
    private LocalDate before3MonthDate;

    public static LineResponseDto from(List<List<LineQueryDto>> list){
        while(list.size() <=2){
            list.add(new ArrayList<>());
        }
        return new LineResponseDto(list.get(0),list.get(1),list.get(2),LocalDate.now().minusMonths(3));
    }

}
