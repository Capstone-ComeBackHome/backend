package com.comebackhome.integration.disease;

import com.comebackhome.disease.domain.Diagnosis;
import com.comebackhome.disease.domain.DiagnosisDisease;
import com.comebackhome.disease.domain.DiagnosisRepository;
import com.comebackhome.disease.infrastructure.repository.DiagnosisDiseaseJpaRepository;
import com.comebackhome.disease.infrastructure.repository.DiseaseJpaRepository;
import com.comebackhome.disease.presentation.dto.DiagnosisSaveRequest;
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
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

        DiagnosisSaveRequest diagnosisSaveRequest = givenDiagnosisRequest();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .content(createJson(diagnosisSaveRequest))
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

    @Test
    void 마지막_진단_내역_Id_사용해서_진단_내역_조회하기() throws Exception{
        // given
        Long diseaseId1 = diseaseJpaRepository.save(givenDisease("질병1")).getId();
        Long diseaseId2 = diseaseJpaRepository.save(givenDisease("질병2")).getId();
        Long diseaseId3 = diseaseJpaRepository.save(givenDisease("질병3")).getId();

        User user = userRepository.save(givenUser());
        Long diagnosisId = diagnosisRepository.save(Diagnosis.from(user.getId()));

        diagnosisDiseaseJpaRepository.saveAll(
                List.of(
                        DiagnosisDisease.of(diseaseId1,diagnosisId,1),
                        DiagnosisDisease.of(diseaseId2,diagnosisId,2),
                        DiagnosisDisease.of(diseaseId3,diagnosisId,3)
                )
        );

        Long diagnosisId2 = diagnosisRepository.save(Diagnosis.from(user.getId()));
        diagnosisDiseaseJpaRepository.saveAll(
                List.of(
                        DiagnosisDisease.of(diseaseId1,diagnosisId2,1),
                        DiagnosisDisease.of(diseaseId2,diagnosisId2,2),
                        DiagnosisDisease.of(diseaseId3,diagnosisId2,3)
                )
        );

        flushAndClear();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"?lastDiagnosisId="+(diagnosisId2))
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diagnosisResponseList[0].createdDate").isNotEmpty())
                .andExpect(jsonPath("$.diagnosisResponseList[0].diagnosisId").value(diagnosisId))
                .andExpect(jsonPath("$.diagnosisResponseList[0].diseaseNameList[0]",is("질병1")))
                .andExpect(jsonPath("$.diagnosisResponseList[0].diseaseNameList[1]",is("질병2")))
                .andExpect(jsonPath("$.diagnosisResponseList[0].diseaseNameList[2]",is("질병3")))
                .andExpect(jsonPath("$.hasNext",is(false)))
        ;
    }

    @Test
    void 마지막_진단_내역_id_없이_진단_내역_조회하기() throws Exception{
        // given
        Long diseaseId1 = diseaseJpaRepository.save(givenDisease("질병1")).getId();
        Long diseaseId2 = diseaseJpaRepository.save(givenDisease("질병2")).getId();
        Long diseaseId3 = diseaseJpaRepository.save(givenDisease("질병3")).getId();

        User user = userRepository.save(givenUser());
        Long diagnosisId = diagnosisRepository.save(Diagnosis.from(user.getId()));

        diagnosisDiseaseJpaRepository.saveAll(
                List.of(
                        DiagnosisDisease.of(diseaseId1,diagnosisId,1),
                        DiagnosisDisease.of(diseaseId2,diagnosisId,2),
                        DiagnosisDisease.of(diseaseId3,diagnosisId,3)
                )
        );

        Long diagnosisId2 = diagnosisRepository.save(Diagnosis.from(user.getId()));
        diagnosisDiseaseJpaRepository.saveAll(
                List.of(
                        DiagnosisDisease.of(diseaseId1,diagnosisId2,1),
                        DiagnosisDisease.of(diseaseId2,diagnosisId2,2),
                        DiagnosisDisease.of(diseaseId3,diagnosisId2,3)
                )
        );

        flushAndClear();


        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"?size=1")
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diagnosisResponseList[0].createdDate").isNotEmpty())
                .andExpect(jsonPath("$.diagnosisResponseList[0].diagnosisId").value(diagnosisId2))
                .andExpect(jsonPath("$.diagnosisResponseList[0].diseaseNameList[0]",is("질병1")))
                .andExpect(jsonPath("$.diagnosisResponseList[0].diseaseNameList[1]",is("질병2")))
                .andExpect(jsonPath("$.diagnosisResponseList[0].diseaseNameList[2]",is("질병3")))
                .andExpect(jsonPath("$.hasNext",is(true)))
        ;
    }

    @Test
    void 진단_내역이_없는_경우() throws Exception{
        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andDo(print())
        ;
    }


}
