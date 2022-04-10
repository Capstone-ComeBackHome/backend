package com.comebackhome.support.helper;

import com.comebackhome.config.security.dto.UserPrincipal;
import com.comebackhome.user.application.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.application.dto.UserInfoRequestDto;
import com.comebackhome.user.domain.AuthProvider;
import com.comebackhome.user.domain.Role;
import com.comebackhome.user.domain.Sex;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.dto.UserEssentialUpdateDto;
import com.comebackhome.user.domain.dto.UserInfoDto;
import com.comebackhome.user.presentation.dto.UserInfoRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;

public class UserGivenHelper {

    public static User givenUser() {
        return User.builder()
                .email("backtony@gmail.com")
                .authProvider(AuthProvider.google)
                .name("backtony")
                .picture("url")
                .role(Role.USER)
                .build();
    }

    public static User givenUserIncludeInfo() {
        return User.builder()
                .email("cjs1863@gmail.com")
                .name("최준성")
                .picture("picture url")
                .authProvider(AuthProvider.google)
                .role(Role.USER)
                .age(27)
                .sex(Sex.MAN)
                .height(172)
                .weight(65)
                .history("저는 과거에..")
                .FamilyHistory("가족 중에는..")
                .drugHistory("약은..")
                .socialHistory("사회력은..")
                .traumaHistory("외상력은..")
                .build();
    }


    public static Authentication createAuthentication(User user) {
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRoleKey()));
        UserPrincipal principal = UserPrincipal.from(user);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public static UserInfoRequest givenUserInfoRequest(){
        return UserInfoRequest.builder()
                .age(27)
                .sex(Sex.MAN)
                .height(172)
                .weight(65)
                .history("저는 과거에..")
                .FamilyHistory("가족 중에는..")
                .drugHistory("약은..")
                .socialHistory("사회력은..")
                .traumaHistory("외상력은..")
                .build();
    }

    public static UserInfoRequestDto givenUserInfoRequestDto(){
        return givenUserInfoRequest().toUserInfoRequestDto();
    }

    public static UserInfoDto givenUserInfoDto(){
        return givenUserInfoRequestDto().toUserInfoDto();
    }

    public static UserEssentialUpdateRequestDto givenUserEssentialUpdateRequestDto(){
        return UserEssentialUpdateRequestDto.builder()
                .age(27)
                .sex(Sex.MAN)
                .weight(65)
                .height(172)
                .build();
    }

    public static UserEssentialUpdateDto givenUserEssentialUpdateDto(){
        return givenUserEssentialUpdateRequestDto().toUserEssentialUpdateDto();
    }
}
