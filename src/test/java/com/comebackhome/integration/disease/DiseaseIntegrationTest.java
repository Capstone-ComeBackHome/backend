package com.comebackhome.integration.disease;

import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.infrastructure.repository.disease.DiseaseJpaRepository;
import com.comebackhome.support.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDisease;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiseaseIntegrationTest extends IntegrationTest {

    private final String URL = "/api/v1/diseases";
    private final String TOKEN_TYPE = "Bearer ";

    @Autowired DiseaseJpaRepository diseaseJpaRepository;

    @Test
    void diseaseId로_상세정보_가져오기() throws Exception{
        // given
        Disease disease = diseaseJpaRepository.save(givenDisease());

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"?diseaseId="+disease.getId())
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("부정맥")))
                .andExpect(jsonPath("$.definition",is("정의")))
                .andExpect(jsonPath("$.recommendDepartment",is("내과")))
                .andExpect(jsonPath("$.symptom",is("증상")))
                .andExpect(jsonPath("$.cause",is("원인")))
                .andExpect(jsonPath("$.hospitalCare",is("병원 치료 방법")))
        ;
    }

    @Test
    void 여러_질병명으로_간략하게_질병_조회하기() throws Exception{
        // given
        diseaseJpaRepository.save(givenDisease("부정맥"));
        diseaseJpaRepository.save(givenDisease("후두염"));

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"/simple?diseaseNameList=부정맥,후두염")
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.simpleDiseaseList[0].name",is("부정맥")))
                .andExpect(jsonPath("$.simpleDiseaseList[0].definition",is("정의")))
                .andExpect(jsonPath("$.simpleDiseaseList[0].recommendDepartment",is("내과")))
                .andExpect(jsonPath("$.simpleDiseaseList[1].name",is("후두염")))
                .andExpect(jsonPath("$.simpleDiseaseList[1].definition",is("정의")))
                .andExpect(jsonPath("$.simpleDiseaseList[1].recommendDepartment",is("내과")))
        ;
    }

    @Test
    void CSVFile로_Disease_저장하기() throws Exception{
        // given
        MockMultipartFile file = createMockMultipartFile();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.multipart(URL)
                .file(file))
                .andExpect(status().isOk())
        ;

        List<Disease> result = diseaseJpaRepository.findAll();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("질병1");
    }

    private MockMultipartFile createMockMultipartFile() {
        return new MockMultipartFile("file",
                "disease.csv",
                "text/csv",
                "질병1,정의,내과,증상,원인,치료".getBytes());
    }
}
