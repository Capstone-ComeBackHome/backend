package com.comebackhome.support;

import com.comebackhome.authentication.infrastructure.repository.LogoutAccessTokenRedisRepository;
import com.comebackhome.authentication.infrastructure.repository.LogoutRefreshTokenRedisRepository;
import com.comebackhome.user.infrastructure.repository.UserJpaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

    @Autowired protected MockMvc mockMvc;
    @Autowired protected ObjectMapper objectMapper;

    @Autowired UserJpaRepository userJpaRepository;
    @Autowired LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;
    @Autowired LogoutRefreshTokenRedisRepository logoutRefreshTokenRedisRepository;

    protected String createJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    @BeforeEach
    void clearUser(){
        userJpaRepository.deleteAllInBatch();
        logoutAccessTokenRedisRepository.deleteAll();
        logoutRefreshTokenRedisRepository.deleteAll();
    }

}
