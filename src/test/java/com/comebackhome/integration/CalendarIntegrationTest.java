package com.comebackhome.integration;

import com.comebackhome.authentication.application.TokenProvider;
import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.ScheduleDiseaseTag;
import com.comebackhome.calendar.domain.repository.DiseaseTagRepository;
import com.comebackhome.calendar.infrastructure.repository.ScheduleDiseaseTagJpaRepository;
import com.comebackhome.calendar.presentation.dto.ScheduleSaveRequest;
import com.comebackhome.support.IntegrationTest;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.comebackhome.calendar.domain.DiseaseType.*;
import static com.comebackhome.support.helper.CalendarGivenHelper.*;
import static com.comebackhome.support.helper.UserGivenHelper.createAuthentication;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CalendarIntegrationTest extends IntegrationTest {

    private final String URL = "/api/v1/calendars";
    private final String TOKEN_TYPE = "Bearer ";

    @Autowired UserRepository userRepository;
    @Autowired TokenProvider tokenProvider;
    @Autowired DiseaseTagRepository diseaseTagRepository;
    @Autowired ScheduleDiseaseTagJpaRepository scheduleDiseaseTagJpaRepository;

    @Test
    void 이미_존재하는_diseaseTag로_스케줄_저장하기() throws Exception{
        // given
        diseaseTagRepository.saveAll(List.of(
                givenDiseaseTag(HEAD,"두통"),
                givenDiseaseTag(SKIN,"여드름"),
                givenDiseaseTag(CUSTOM,"교통사고")));

        ScheduleSaveRequest scheduleSaveRequest = givenScheduleSaveRequest();
        scheduleSaveRequest.setDiseaseTagRequestList(List.of(
                givenDiseaseTagRequest(DiseaseType.HEAD,"두통"),
                givenDiseaseTagRequest(DiseaseType.SKIN,"여드름"),
                givenDiseaseTagRequest(DiseaseType.CUSTOM,"교통사고")
        ));

        // when then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken())
                .content(createJson(scheduleSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

        List<ScheduleDiseaseTag> result = scheduleDiseaseTagJpaRepository.findAll();
        Set<Long> scheduleIdSet = result.stream().map(scheduleDiseaseTag -> scheduleDiseaseTag.getSchedule().getId()).collect(Collectors.toSet());

        assertThat(result.size()).isEqualTo(3);
        assertThat(scheduleIdSet.size()).isEqualTo(1);
    }

    @Test
    void 존재하지_않는_customType_diseaseTag로_스케줄_저장하면_CustomType_diseaseTag를_save하고_스케줄을_저장한다() throws Exception{
        // given
        diseaseTagRepository.saveAll(List.of(
                givenDiseaseTag(HEAD,"두통"),
                givenDiseaseTag(SKIN,"여드름"),
                givenDiseaseTag(CUSTOM,"교통사고")));


        ScheduleSaveRequest scheduleSaveRequest = givenScheduleSaveRequest();
        scheduleSaveRequest.setDiseaseTagRequestList(List.of(
                givenDiseaseTagRequest(DiseaseType.HEAD,"두통"),
                givenDiseaseTagRequest(DiseaseType.SKIN,"여드름"),
                givenDiseaseTagRequest(DiseaseType.CUSTOM,"교통사고"),
                givenDiseaseTagRequest(DiseaseType.CUSTOM,"허리디스크")
        ));

        // when then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken())
                .content(createJson(scheduleSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

        List<ScheduleDiseaseTag> result = scheduleDiseaseTagJpaRepository.findAll();
        Set<Long> scheduleIdSet = result.stream().map(scheduleDiseaseTag -> scheduleDiseaseTag.getSchedule().getId()).collect(Collectors.toSet());

        assertThat(result.size()).isEqualTo(4);
        assertThat(scheduleIdSet.size()).isEqualTo(1);
    }

    @Test
    void customType이_없이_스케줄_저장() throws Exception{
        // given
        diseaseTagRepository.saveAll(List.of(
                givenDiseaseTag(HEAD,"두통"),
                givenDiseaseTag(SKIN,"여드름")
                ));


        ScheduleSaveRequest scheduleSaveRequest = givenScheduleSaveRequest();
        scheduleSaveRequest.setDiseaseTagRequestList(List.of(
                givenDiseaseTagRequest(DiseaseType.HEAD,"두통"),
                givenDiseaseTagRequest(DiseaseType.SKIN,"여드름")
        ));

        // when then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken())
                .content(createJson(scheduleSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

        List<ScheduleDiseaseTag> result = scheduleDiseaseTagJpaRepository.findAll();
        Set<Long> scheduleIdSet = result.stream().map(scheduleDiseaseTag -> scheduleDiseaseTag.getSchedule().getId()).collect(Collectors.toSet());

        assertThat(result.size()).isEqualTo(2);
        assertThat(scheduleIdSet.size()).isEqualTo(1);
    }

    private String createAccessToken() {
        User user = userRepository.save(givenUser());
        Authentication authentication = createAuthentication(user);
        return tokenProvider.createAccessToken(authentication);
    }
}
