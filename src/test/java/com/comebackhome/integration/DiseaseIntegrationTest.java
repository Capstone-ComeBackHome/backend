package com.comebackhome.integration;

import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.infrastructure.repository.CauseJpaRepository;
import com.comebackhome.disease.infrastructure.repository.DiseaseJpaRepository;
import com.comebackhome.disease.infrastructure.repository.HomeCareJpaRepository;
import com.comebackhome.support.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.comebackhome.support.DiseaseGivenHelper.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiseaseIntegrationTest extends IntegrationTest {

    private final String URL = "/api/v1/disease";

    @Autowired DiseaseJpaRepository diseaseJpaRepository;
    @Autowired CauseJpaRepository causeJpaRepository;
    @Autowired HomeCareJpaRepository homeCareJpaRepository;


    @Test
    void diseaseId로_상세정보_가져오기() throws Exception{
        // given
        Disease disease = diseaseJpaRepository.save(givenDisease());
        causeJpaRepository.save(givenCause(disease,"원인"));
        homeCareJpaRepository.save(givenHomeCare(disease,"해결책"));

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"?diseaseId="+disease.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("부정맥")))
                .andExpect(jsonPath("$.definition",is("정의")))
                .andExpect(jsonPath("$.recommendDepartment",is("내과")))
                .andExpect(jsonPath("$.symptom",is("증상")))
                .andExpect(jsonPath("$.causeList[0]",is("원인")))
                .andExpect(jsonPath("$.hospitalCare",is("병원 치료 방법")))
                .andExpect(jsonPath("$.homeCareList[0]",is("해결책")))
                .andExpect(jsonPath("$.complications",is("합병증")))
        ;
    }
}
