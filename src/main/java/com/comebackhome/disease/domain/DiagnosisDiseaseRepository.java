package com.comebackhome.disease.domain;

import java.util.List;

public interface DiagnosisDiseaseRepository {

    void saveAll(List<DiagnosisDisease> diagnosisDiseaseList);
}
