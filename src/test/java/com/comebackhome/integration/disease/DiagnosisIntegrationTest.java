package com.comebackhome.integration.disease;

import com.comebackhome.disease.infrastructure.repository.DiseaseJpaRepository;
import com.comebackhome.disease.presentation.dto.DiagnosisRequest;
import com.comebackhome.support.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosisRequest;
import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDisease;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiagnosisIntegrationTest extends IntegrationTest {

    private final String URL = "/api/v1/diagnoses";
    private final String TOKEN_TYPE = "Bearer ";

    @Autowired DiseaseJpaRepository diseaseJpaRepository;

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
}
