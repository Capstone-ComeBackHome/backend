package com.comebackhome.authentication.domain.repository;

import com.comebackhome.authentication.domain.LogoutAccessToken;
import com.comebackhome.authentication.domain.LogoutRefreshToken;
import com.comebackhome.authentication.domain.RefreshToken;

public interface TokenRepository {

    void saveLogoutAccessToken(LogoutAccessToken logoutAccessToken);

    void saveLogoutRefreshToken(LogoutRefreshToken logoutRefreshToken);

    void saveRefreshToken(RefreshToken refreshToken);

    boolean existsLogoutAccessTokenById(String token);

    boolean existsLogoutRefreshTokenById(String token);

    boolean existsRefreshTokenById(String token);

    void deleteRefreshTokenById(String token);

}
