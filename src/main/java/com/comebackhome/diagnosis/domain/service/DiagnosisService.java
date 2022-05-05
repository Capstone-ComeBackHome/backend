package com.comebackhome.diagnosis.domain.service;

import com.comebackhome.common.exception.disease.DiagnosisNotFoundException;
import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.common.exception.disease.NotMyDiagnosisException;
import com.comebackhome.diagnosis.domain.Diagnosis;
import com.comebackhome.diagnosis.domain.disease.DiagnosisDisease;
import com.comebackhome.diagnosis.domain.disease.repository.DiagnosisDiseaseRepository;
import com.comebackhome.diagnosis.domain.disease.repository.DiseaseRepository;
import com.comebackhome.diagnosis.domain.repository.DiagnosisRepository;
import com.comebackhome.diagnosis.domain.service.dto.DiagnosisResponseDto;
import com.comebackhome.diagnosis.domain.service.dto.DiagnosisResponseDtoList;
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
        for (String diseaseName : diseaseNameList) {
            Long diseaseId = getDiseaseIdOrThrow(diseaseName);
            diagnosisDiseaseList.add(DiagnosisDisease.of(diseaseId, diagnosisId));
        }

        return diagnosisDiseaseList;
    }

    private Long getDiseaseIdOrThrow(String diseaseName) {
        Long diseaseId = diseaseRepository.findIdByName(diseaseName.trim())
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
