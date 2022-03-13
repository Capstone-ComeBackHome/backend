package com.comebackhome.authentication.infrastructure.repository;

import com.comebackhome.authentication.domain.LogoutRefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface LogoutRefreshTokenRedisRepository extends CrudRepository<LogoutRefreshToken,String> {
}
