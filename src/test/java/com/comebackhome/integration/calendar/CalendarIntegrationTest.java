package com.comebackhome.integration.calendar;

import com.comebackhome.calendar.domain.diseasetag.DiseaseTag;
import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.diseasetag.repository.DiseaseTagRepository;
import com.comebackhome.calendar.domain.schedule.Schedule;
import com.comebackhome.calendar.domain.schedule.ScheduleDiseaseTag;
import com.comebackhome.calendar.domain.schedule.repository.ScheduleRepository;
import com.comebackhome.calendar.infrastructure.repository.diseasetag.DiseaseTagJpaRepository;
import com.comebackhome.calendar.infrastructure.repository.schedulediseasetag.ScheduleDiseaseTagJpaRepository;
import com.comebackhome.calendar.presentation.dto.request.ScheduleModifyRequest;
import com.comebackhome.calendar.presentation.dto.request.ScheduleSaveRequest;
import com.comebackhome.support.IntegrationTest;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.comebackhome.calendar.domain.diseasetag.DiseaseType.*;
import static com.comebackhome.support.helper.CalendarGivenHelper.*;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CalendarIntegrationTest extends IntegrationTest {

    private final String URL = "/api/v1/calendars";
    private final String TOKEN_TYPE = "Bearer ";

    @Autowired UserRepository userRepository;
    @Autowired DiseaseTagRepository diseaseTagRepository;
    @Autowired ScheduleDiseaseTagJpaRepository scheduleDiseaseTagJpaRepository;
    @Autowired ScheduleRepository scheduleRepository;
    @Autowired DiseaseTagJpaRepository diseaseTagJpaRepository;

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

    @Test
    void 스케줄_삭제() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        Long scheduleId = saveSchedule(user);

        // when then
        mockMvc.perform(MockMvcRequestBuilders.delete(URL+"/"+scheduleId)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }

    private Long saveSchedule(User user) {
        Long scheduleId = scheduleRepository.save(givenSchedule(user));
        Long diseaseTagId1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통")).getId();
        Long diseaseTagId2 = diseaseTagJpaRepository.save(givenDiseaseTag(SKIN, "여드름")).getId();
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId,diseaseTagId1));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId,diseaseTagId2));
        return scheduleId;
    }

    @Test
    void 특정_월의_나의_스케줄_조회() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        Long diseaseTagId1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통")).getId();
        Long diseaseTagId2 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "디스크")).getId();
        Long diseaseTagId3 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "교통사고")).getId();

        Long scheduleId1 = scheduleRepository.save(givenSchedule(user, LocalDate.of(2022,5,3)));
        scheduleDiseaseTagJpaRepository.saveAll(List.of(
                ScheduleDiseaseTag.of(scheduleId1,diseaseTagId1),
                ScheduleDiseaseTag.of(scheduleId1,diseaseTagId2),
                ScheduleDiseaseTag.of(scheduleId1,diseaseTagId3)
        ));

        Long scheduleId2 = scheduleRepository.save(givenSchedule(user,LocalDate.of(2022,5,1)));
        scheduleDiseaseTagJpaRepository.saveAll(List.of(
                ScheduleDiseaseTag.of(scheduleId2,diseaseTagId1),
                ScheduleDiseaseTag.of(scheduleId2,diseaseTagId2)
        ));

        flushAndClear();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"?yearMonth=2022-05")
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.scheduleResponseList[0].scheduleId").value(scheduleId2))
                .andExpect(jsonPath("$.data.scheduleResponseList[0].scheduleDate").value(LocalDate.of(2022,5,1).toString()))
                .andExpect(jsonPath("$.data.scheduleResponseList[0].dailyNote").value("오늘은 조금 괜찮아요."))
                .andExpect(jsonPath("$.data.scheduleResponseList[0].painType").value("GOOD"))
                .andExpect(jsonPath("$.data.scheduleResponseList[0].diseaseTagResponseList[0].diseaseType").value("HEAD"))
                .andExpect(jsonPath("$.data.scheduleResponseList[0].diseaseTagResponseList[0].name").value("두통"))
                .andExpect(jsonPath("$.data.scheduleResponseList[0].diseaseTagResponseList[1].diseaseType").value("CUSTOM"))
                .andExpect(jsonPath("$.data.scheduleResponseList[0].diseaseTagResponseList[1].name").value("디스크"))

                .andExpect(jsonPath("$.data.scheduleResponseList[1].scheduleId").value(scheduleId1))
                .andExpect(jsonPath("$.data.scheduleResponseList[1].scheduleDate").value(LocalDate.of(2022,5,3).toString()))
                .andExpect(jsonPath("$.data.scheduleResponseList[1].dailyNote").value("오늘은 조금 괜찮아요."))
                .andExpect(jsonPath("$.data.scheduleResponseList[1].painType").value("GOOD"))
                .andExpect(jsonPath("$.data.scheduleResponseList[1].diseaseTagResponseList[0].diseaseType").value("HEAD"))
                .andExpect(jsonPath("$.data.scheduleResponseList[1].diseaseTagResponseList[0].name").value("두통"))
                .andExpect(jsonPath("$.data.scheduleResponseList[1].diseaseTagResponseList[1].diseaseType").value("CUSTOM"))
                .andExpect(jsonPath("$.data.scheduleResponseList[1].diseaseTagResponseList[1].name").value("디스크"))
                .andExpect(jsonPath("$.data.scheduleResponseList[1].diseaseTagResponseList[2].diseaseType").value("CUSTOM"))
                .andExpect(jsonPath("$.data.scheduleResponseList[1].diseaseTagResponseList[2].name").value("교통사고"))
                .andExpectAll(
                        expectCommonSuccess()
                )
        ;
    }

    @Test
    void 나의_특정_스케줄_조회() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        Long diseaseTagId1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통")).getId();
        Long diseaseTagId2 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "디스크")).getId();
        Long diseaseTagId3 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "교통사고")).getId();

        Schedule schedule = givenSchedule(user);
        Long scheduleId = scheduleRepository.save(schedule);
        scheduleDiseaseTagJpaRepository.saveAll(List.of(
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId1),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId2),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId3)
        ));

        flushAndClear();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"/"+ scheduleId)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.scheduleId").value(scheduleId))
                .andExpect(jsonPath("$.data.scheduleDate").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.data.dailyNote").value(schedule.getDailyNote()))
                .andExpect(jsonPath("$.data.painType").value(schedule.getPainType().name()))
                .andExpect(jsonPath("$.data.diseaseTagResponseList[0].diseaseType").value("HEAD"))
                .andExpect(jsonPath("$.data.diseaseTagResponseList[0].name").value("두통"))
                .andExpect(jsonPath("$.data.diseaseTagResponseList[1].diseaseType").value("CUSTOM"))
                .andExpect(jsonPath("$.data.diseaseTagResponseList[1].name").value("디스크"))
                .andExpect(jsonPath("$.data.diseaseTagResponseList[2].diseaseType").value("CUSTOM"))
                .andExpect(jsonPath("$.data.diseaseTagResponseList[2].name").value("교통사고"))
                .andExpectAll(
                        expectCommonSuccess()
                )
        ;
    }

    @Test
    void 증상_변경_없이_일기와_증상_정도만_수정() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        Long diseaseTagId1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통")).getId();
        Long diseaseTagId2 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "디스크")).getId();
        Long diseaseTagId3 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "교통사고")).getId();

        Schedule schedule = givenSchedule(user);
        Long scheduleId = scheduleRepository.save(schedule);
        scheduleDiseaseTagJpaRepository.saveAll(List.of(
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId1),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId2),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId3)
        ));

        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();
        scheduleModifyRequest.setDiseaseTagRequestList(
                List.of(
                        givenDiseaseTagRequest(HEAD,"두통"),
                        givenDiseaseTagRequest(CUSTOM,"디스크"),
                        givenDiseaseTagRequest(CUSTOM,"교통사고")
                )
        );


        flushAndClear();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL+"/"+ scheduleId)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

        flushAndClear();

        Schedule result = scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(scheduleId, user.getId()).get();

        assertThat(result.getDailyNote()).isEqualTo(scheduleModifyRequest.getDailyNote());
        assertThat(result.getPainType()).isEqualTo(scheduleModifyRequest.getPainType());
        assertThat(result.getScheduleDiseaseTagList().size()).isEqualTo(3);
    }


    @Test
    void 디폴트_증상_하나만_추가된_경우() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        Long diseaseTagId1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통")).getId();
        Long diseaseTagId2 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "디스크")).getId();
        Long diseaseTagId3 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "교통사고")).getId();
        Long diseaseTagId4 = diseaseTagJpaRepository.save(givenDiseaseTag(SKIN, "여드름")).getId();


        Schedule schedule = givenSchedule(user);
        Long scheduleId = scheduleRepository.save(schedule);
        scheduleDiseaseTagJpaRepository.saveAll(List.of(
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId1),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId2),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId3)
        ));

        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();
        scheduleModifyRequest.setDiseaseTagRequestList(
                List.of(
                        givenDiseaseTagRequest(HEAD,"두통"),
                        givenDiseaseTagRequest(CUSTOM,"디스크"),
                        givenDiseaseTagRequest(CUSTOM,"교통사고"),
                        givenDiseaseTagRequest(SKIN,"여드름")
                )
        );

        flushAndClear();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL+"/"+ scheduleId)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

        flushAndClear();

        Schedule result = scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(scheduleId, user.getId()).get();

        assertThat(result.getScheduleDiseaseTagList().size()).isEqualTo(4);
    }

    @Test
    void 없는_커스텀_증상이_추가된_경우() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        Long diseaseTagId1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통")).getId();
        Long diseaseTagId2 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "디스크")).getId();
        Long diseaseTagId3 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "교통사고")).getId();


        Schedule schedule = givenSchedule(user);
        Long scheduleId = scheduleRepository.save(schedule);
        scheduleDiseaseTagJpaRepository.saveAll(List.of(
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId1),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId2),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId3)
        ));

        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();
        scheduleModifyRequest.setDiseaseTagRequestList(
                List.of(
                        givenDiseaseTagRequest(HEAD,"두통"),
                        givenDiseaseTagRequest(CUSTOM,"디스크"),
                        givenDiseaseTagRequest(CUSTOM,"교통사고"),
                        givenDiseaseTagRequest(CUSTOM,"목디스크")
                )
        );

        flushAndClear();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL+"/"+ scheduleId)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

        flushAndClear();

        Schedule result = scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(scheduleId, user.getId()).get();

        assertThat(result.getScheduleDiseaseTagList().size()).isEqualTo(4);
    }


    @Test
    void 하나는_등록된_커스텀_하나는_등록되지_않은_커스텀_증상이_추가된_경우() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        Long diseaseTagId1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통")).getId();
        Long diseaseTagId2 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "디스크")).getId();
        Long diseaseTagId3 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "교통사고")).getId();
        Long diseaseTagId4 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "목디스크")).getId();


        Schedule schedule = givenSchedule(user);
        Long scheduleId = scheduleRepository.save(schedule);
        scheduleDiseaseTagJpaRepository.saveAll(List.of(
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId1),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId2),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId3)
        ));

        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();
        scheduleModifyRequest.setDiseaseTagRequestList(
                List.of(
                        givenDiseaseTagRequest(HEAD,"두통"),
                        givenDiseaseTagRequest(CUSTOM,"디스크"),
                        givenDiseaseTagRequest(CUSTOM,"교통사고"),
                        givenDiseaseTagRequest(CUSTOM,"목디스크"),
                        givenDiseaseTagRequest(CUSTOM,"식곤증")
                )
        );

        flushAndClear();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL+"/"+ scheduleId)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

        flushAndClear();

        Schedule result = scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(scheduleId, user.getId()).get();

        assertThat(result.getScheduleDiseaseTagList().size()).isEqualTo(5);
    }

    @Test
    void 증상이_하나_삭제된_경우() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        Long diseaseTagId1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통")).getId();
        Long diseaseTagId2 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "디스크")).getId();
        Long diseaseTagId3 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "교통사고")).getId();


        Schedule schedule = givenSchedule(user);
        Long scheduleId = scheduleRepository.save(schedule);
        scheduleDiseaseTagJpaRepository.saveAll(List.of(
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId1),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId2),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId3)
        ));

        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();
        scheduleModifyRequest.setDiseaseTagRequestList(
                List.of(
                        givenDiseaseTagRequest(HEAD,"두통"),
                        givenDiseaseTagRequest(CUSTOM,"디스크")
                )
        );

        flushAndClear();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL+"/"+ scheduleId)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

        flushAndClear();

        Schedule result = scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(scheduleId, user.getId()).get();

        assertThat(result.getScheduleDiseaseTagList().size()).isEqualTo(2);
    }

    @Test
    void 증상이_하나_삭제_하나_추가된_경우() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        Long diseaseTagId1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통")).getId();
        Long diseaseTagId2 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "디스크")).getId();
        Long diseaseTagId3 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "교통사고")).getId();


        Schedule schedule = givenSchedule(user);
        Long scheduleId = scheduleRepository.save(schedule);
        scheduleDiseaseTagJpaRepository.saveAll(List.of(
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId1),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId2),
                ScheduleDiseaseTag.of(scheduleId,diseaseTagId3)
        ));

        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();
        scheduleModifyRequest.setDiseaseTagRequestList(
                List.of(
                        givenDiseaseTagRequest(HEAD,"두통"),
                        givenDiseaseTagRequest(CUSTOM,"디스크"),
                        givenDiseaseTagRequest(CUSTOM,"목디스크")
                )
        );

        flushAndClear();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL+"/"+ scheduleId)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

        flushAndClear();

        Schedule result = scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(scheduleId, user.getId()).get();

        assertThat(result.getScheduleDiseaseTagList().size()).isEqualTo(3);
    }

    @Test
    void bubble_그래프_데이터_조회() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        Long scheduleId = scheduleRepository.save(givenSchedule(user));
        DiseaseTag diseaseTag1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통"));
        DiseaseTag diseaseTag2 = diseaseTagJpaRepository.save(givenDiseaseTag(SKIN, "여드름"));
        DiseaseTag diseaseTag3 = diseaseTagJpaRepository.save(givenDiseaseTag(SKIN, "피부염"));
        DiseaseTag diseaseTag4 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "교통사고"));

        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId,diseaseTag1.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId,diseaseTag2.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId,diseaseTag3.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId,diseaseTag4.getId()));

        flushAndClear();


        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"/statistics/bubble")
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.bubbleResponseList[0].diseaseType").value("HEAD"))
                .andExpect(jsonPath("$.data.bubbleResponseList[0].count").value(1))
                .andExpect(jsonPath("$.data.bubbleResponseList[0].painAverage").value(1.0))
                .andExpect(jsonPath("$.data.bubbleResponseList[1].diseaseType").value("BRONCHUS"))
                .andExpect(jsonPath("$.data.bubbleResponseList[1].count").value(0))
                .andExpect(jsonPath("$.data.bubbleResponseList[1].painAverage").value(0.0))
                .andExpect(jsonPath("$.data.bubbleResponseList[2].diseaseType").value("CHEST"))
                .andExpect(jsonPath("$.data.bubbleResponseList[2].count").value(0))
                .andExpect(jsonPath("$.data.bubbleResponseList[2].painAverage").value(0.0))
                .andExpect(jsonPath("$.data.bubbleResponseList[3].diseaseType").value("STOMACH"))
                .andExpect(jsonPath("$.data.bubbleResponseList[3].count").value(0))
                .andExpect(jsonPath("$.data.bubbleResponseList[3].painAverage").value(0.0))
                .andExpect(jsonPath("$.data.bubbleResponseList[4].diseaseType").value("LIMB"))
                .andExpect(jsonPath("$.data.bubbleResponseList[4].count").value(0))
                .andExpect(jsonPath("$.data.bubbleResponseList[4].painAverage").value(0.0))
                .andExpect(jsonPath("$.data.bubbleResponseList[5].diseaseType").value("SKIN"))
                .andExpect(jsonPath("$.data.bubbleResponseList[5].count").value(2))
                .andExpect(jsonPath("$.data.bubbleResponseList[5].painAverage").value(1.0))
                .andExpectAll(
                        expectCommonSuccess()
                )
        ;
    }

    @Test
    void line_그래프_데이터_조회() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        DiseaseTag diseaseTag1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통"));
        DiseaseTag diseaseTag2 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "건망증"));
        DiseaseTag diseaseTag3 = diseaseTagJpaRepository.save(givenDiseaseTag(SKIN, "여드름"));
        DiseaseTag diseaseTag4 = diseaseTagJpaRepository.save(givenDiseaseTag(SKIN, "피부염"));
        DiseaseTag diseaseTag5 = diseaseTagJpaRepository.save(givenDiseaseTag(CUSTOM, "교통사고"));

        Long scheduleId1 = scheduleRepository.save(givenScheduleBeforeDay(user, 1));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId1,diseaseTag1.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId1,diseaseTag2.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId1,diseaseTag3.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId1,diseaseTag4.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId1,diseaseTag5.getId()));

        Long scheduleId2 = scheduleRepository.save(givenScheduleBeforeDay(user, 2));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId2,diseaseTag1.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId2,diseaseTag2.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId2,diseaseTag3.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId2,diseaseTag5.getId()));

        Long scheduleId3 = scheduleRepository.save(givenScheduleBeforeDay(user, 3));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId3,diseaseTag1.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId3,diseaseTag2.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId3,diseaseTag5.getId()));

        Long scheduleId4 = scheduleRepository.save(givenScheduleBeforeMonth(user, 3));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId4,diseaseTag1.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId4,diseaseTag5.getId()));

        Long scheduleId5 = scheduleRepository.save(givenScheduleBeforeMonth(user, 4));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId5,diseaseTag1.getId()));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId5,diseaseTag5.getId()));

        flushAndClear();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"/statistics/line")
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.top1.length()").value(4))
                .andExpect(jsonPath("$.data.top1[0].scheduleDate").value(LocalDate.now().minusMonths(3).toString()))
                .andExpect(jsonPath("$.data.top1[0].diseaseName").value("두통"))

                .andExpect(jsonPath("$.data.top2.length()").value(3))
                .andExpect(jsonPath("$.data.top2[0].scheduleDate").value(LocalDate.now().minusDays(3).toString()))
                .andExpect(jsonPath("$.data.top2[0].diseaseName").value("건망증"))

                .andExpect(jsonPath("$.data.top3.length()").value(2))
                .andExpect(jsonPath("$.data.top3[0].scheduleDate").value(LocalDate.now().minusDays(2).toString()))
                .andExpect(jsonPath("$.data.top3[0].diseaseName").value("여드름"))

                .andExpect(jsonPath("$.data.before3MonthDate").value(LocalDate.now().minusMonths(3).toString()))

                .andExpectAll(
                        expectCommonSuccess()
                )
        ;

    }

    @Test
    void line_그래프_데이터_조회_단건만_있는_경우() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        DiseaseTag diseaseTag1 = diseaseTagJpaRepository.save(givenDiseaseTag(HEAD, "두통"));

        Long scheduleId1 = scheduleRepository.save(givenScheduleBeforeDay(user, 1));
        scheduleDiseaseTagJpaRepository.save(ScheduleDiseaseTag.of(scheduleId1,diseaseTag1.getId()));
        flushAndClear();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"/statistics/line")
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.top1.length()").value(1))
                .andExpect(jsonPath("$.data.top1[0].scheduleDate").value(LocalDate.now().minusDays(1).toString()))
                .andExpect(jsonPath("$.data.top1[0].diseaseName").value("두통"))
                .andExpectAll(
                        expectCommonSuccess()
                )
        ;

    }

}
