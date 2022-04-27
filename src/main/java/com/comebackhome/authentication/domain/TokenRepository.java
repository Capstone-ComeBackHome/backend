package com.comebackhome.authentication.domain;

public interface TokenRepository {

    void saveLogoutAccessToken(LogoutAccessToken logoutAccessToken);

    void saveLogoutRefreshToken(LogoutRefreshToken logoutRefreshToken);

    void saveRefreshToken(RefreshToken refreshToken);

    boolean existsLogoutAccessTokenById(String token);

    boolean existsLogoutRefreshTokenById(String token);

    boolean existsRefreshTokenById(String token);

    void deleteRefreshTokenById(String token);

}
