package com.comebackhome.calendar.domain.service;

import com.comebackhome.calendar.domain.service.dto.request.DiseaseTagRequestDto;

import java.util.List;

public interface ScheduleDiseaseSeriesFactory {

    void save(Long scheduleId, List<DiseaseTagRequestDto> diseaseTagRequestDtoList);
}
