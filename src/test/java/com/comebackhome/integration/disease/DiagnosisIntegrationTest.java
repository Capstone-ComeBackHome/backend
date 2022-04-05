package com.comebackhome.integration.disease;

import com.comebackhome.disease.domain.Diagnosis;
import com.comebackhome.disease.domain.DiagnosisDisease;
import com.comebackhome.disease.domain.DiagnosisRepository;
import com.comebackhome.disease.infrastructure.repository.DiagnosisDiseaseJpaRepository;
import com.comebackhome.disease.infrastructure.repository.DiseaseJpaRepository;
import com.comebackhome.disease.presentation.dto.DiagnosisRequest;
import com.comebackhome.support.IntegrationTest;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosisRequest;
import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDisease;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiagnosisIntegrationTest extends IntegrationTest {

    private final String URL = "/api/v1/diagnoses";
    private final String TOKEN_TYPE = "Bearer ";

    @Autowired DiseaseJpaRepository diseaseJpaRepository;
    @Autowired UserRepository userRepository;
    @Autowired DiagnosisRepository diagnosisRepository;
    @Autowired DiagnosisDiseaseJpaRepository diagnosisDiseaseJpaRepository;

    @Test
    void 세개의_질병명으로_진단_저장하기() throws Exception{
        // given
        diseaseJpaRepository.saveAll(List.of(givenDisease("질병1"),givenDisease("질병2"),givenDisease("질병3")));

        DiagnosisRequest diagnosisRequest = givenDiagnosisRequest();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .content(createJson(diagnosisRequest))
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }

    @Test
    void 진단_내역_삭제하기() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        Long diagnosisId = diagnosisRepository.save(Diagnosis.from(user.getId()));
        Long diseaseId = diseaseJpaRepository.save(givenDisease()).getId();
        diagnosisDiseaseJpaRepository.save(DiagnosisDisease.of(diseaseId,diagnosisId,1));


        // when then
        mockMvc.perform(MockMvcRequestBuilders.delete(URL+"/"+diagnosisId)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }


}
