package com.comebackhome.calendar.domain.schedule.service;

import com.comebackhome.calendar.domain.schedule.service.dto.request.DiseaseTagRequestDto;

import java.util.List;

public interface ScheduleDiseaseSeriesFactory {

    void save(Long scheduleId, List<DiseaseTagRequestDto> diseaseTagRequestDtoList);
}
