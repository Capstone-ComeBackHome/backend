package com.comebackhome.disease.application;

import com.comebackhome.common.exception.disease.DiagnosisNotFoundException;
import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.common.exception.disease.NotMyDiagnosisException;
import com.comebackhome.disease.application.dto.DiagnosisResponseDto;
import com.comebackhome.disease.application.dto.DiagnosisResponseDtoList;
import com.comebackhome.disease.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DiagnosisService implements DiagnosisCommandUseCase, DiagnosisQueryUseCase{

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

    @Override
    @Transactional(readOnly = true)
    public DiagnosisResponseDtoList getMyDiagnoses(Long lastDiagnosisId, Long userId, Pageable pageable) {
        Slice<Diagnosis> diagnosisList
                = diagnosisRepository.findDiagnosisListByLastDiagnosisIdAndUserId(lastDiagnosisId, userId, pageable);
        return DiagnosisResponseDtoList.of(createDiagnosisResponseDtoList(diagnosisList),diagnosisList.hasNext());
    }

    private List<DiagnosisResponseDto> createDiagnosisResponseDtoList(Slice<Diagnosis> diagnosisList) {
        List<DiagnosisResponseDto> diagnosisResponseDtoList = new ArrayList<>();
        for (Diagnosis diagnosis : diagnosisList.getContent()) {
            List<String> diseaseNameList = diagnosis.getDiagnosisDiseaseList().parallelStream()
                    .map(diagnosisDisease -> diagnosisDisease.getDisease().getName())
                    .collect(Collectors.toList());
            diagnosisResponseDtoList.add(DiagnosisResponseDto.of(diagnosis,diseaseNameList));
        }
        return diagnosisResponseDtoList;
    }
}
