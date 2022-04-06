package com.comebackhome.unit.user.infrastructure;

import com.comebackhome.support.JpaRepositoryTest;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.UserRepository;
import com.comebackhome.user.infrastructure.repository.UserJpaRepository;
import com.comebackhome.user.infrastructure.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryImplTest extends JpaRepositoryTest {

    @Autowired UserJpaRepository userJpaRepository;

    UserRepository userRepository;

    @BeforeEach
    void setup(){
        userRepository = new UserRepositoryImpl(userJpaRepository);
    }

    @Test
    void User_저장() throws Exception{

        //when
        User savedUser = userRepository.save(givenUser());

        //then
        assertThat(userJpaRepository.existsById(savedUser.getId())).isTrue();
    }

    @Test
    void 이메일로_유저_찾기() throws Exception{
        //given
        User savedUser = userRepository.save(givenUser());

        //when
        Optional<User> userOptional = userRepository.findByEmail(savedUser.getEmail());
        
        //then
        assertThat(userOptional.get()).isNotNull();
    }

    @Test
    void Id로_유저_찾기() throws Exception{
        //given
        User savedUser = userRepository.save(givenUser());

        //when
        Optional<User> result = userRepository.findById(savedUser.getId());

        //then
        assertThat(result).isNotEmpty();
    }

}
