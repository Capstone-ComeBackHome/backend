package com.comebackhome.support;

import com.comebackhome.authentication.domain.service.TokenProvider;
import com.comebackhome.authentication.infrastructure.repository.LogoutAccessTokenRedisRepository;
import com.comebackhome.authentication.infrastructure.repository.LogoutRefreshTokenRedisRepository;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.infrastructure.repository.UserJpaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.comebackhome.support.helper.UserGivenHelper.createAuthentication;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

    @Autowired protected MockMvc mockMvc;
    @Autowired protected ObjectMapper objectMapper;
    @Autowired protected EntityManager em;

    @Autowired protected TokenProvider tokenProvider;
    @Autowired UserJpaRepository userJpaRepository;
    @Autowired LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;
    @Autowired LogoutRefreshTokenRedisRepository logoutRefreshTokenRedisRepository;


    @BeforeEach
    void clearUser(){
        userJpaRepository.deleteAllInBatch();
        logoutAccessTokenRedisRepository.deleteAll();
        logoutRefreshTokenRedisRepository.deleteAll();
    }

    protected String createJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    protected String createAccessToken() {
        User user = userJpaRepository.save(givenUser());
        Authentication authentication = createAuthentication(user);
        return tokenProvider.createAccessToken(authentication);
    }

    protected String createAccessToken(User user) {
        Authentication authentication = createAuthentication(user);
        return tokenProvider.createAccessToken(authentication);
    }

    protected void flushAndClear(){
        em.flush();
        em.clear();
    }

}
