package com.comebackhome.disease.application;

import com.comebackhome.common.exception.disease.DiagnosisNotFoundException;
import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.common.exception.disease.NotMyDiagnosisException;
import com.comebackhome.disease.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiagnosisService implements DiagnosisCommandUseCase{

    private final DiseaseRepository diseaseRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisDiseaseRepository diagnosisDiseaseRepository;

    @Override
    public void createDiagnosis(List<String> diseaseNameList, Long userId) {
        Long diagnosisId = diagnosisRepository.save(Diagnosis.from(userId));
        List<DiagnosisDisease> diagnosisDiseaseList = createDiagnosisDiseaseList(diseaseNameList, diagnosisId);
        diagnosisDiseaseRepository.saveAll(diagnosisDiseaseList);
    }

    private List<DiagnosisDisease> createDiagnosisDiseaseList(List<String> diseaseNameList, Long diagnosisId) {
        List<DiagnosisDisease> diagnosisDiseaseList = new ArrayList<>();
        for (int order = 0; order < diseaseNameList.size(); order++){
            Long diseaseId = getDiseaseIdOrThrow(diseaseNameList, order);
            diagnosisDiseaseList.add(DiagnosisDisease.of(diseaseId, diagnosisId,order+1));
        }

        return diagnosisDiseaseList;
    }

    private Long getDiseaseIdOrThrow(List<String> diseaseNameList, int order) {
        Long diseaseId = diseaseRepository.findIdByName(diseaseNameList.get(order).trim())
                .orElseThrow(() -> new DiseaseNotFoundException());
        return diseaseId;
    }


    @Override
    public void deleteMyDiagnosis(Long diagnosisId, Long userId) {
        checkIsMyDiagnosis(diagnosisId, userId);
        diagnosisDiseaseRepository.deleteByDiagnosisId(diagnosisId);
        diagnosisRepository.deleteById(diagnosisId);
    }

    private void checkIsMyDiagnosis(Long diagnosisId, Long userId) {
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> new DiagnosisNotFoundException());

        if (!diagnosis.getUser().getId().equals(userId)){
            throw new NotMyDiagnosisException();
        }
    }
}
