package com.comebackhome.authentication.infrastructure.repository;

import com.comebackhome.authentication.domain.LogoutAccessToken;
import org.springframework.data.repository.CrudRepository;

public interface LogoutAccessTokenRedisRepository extends CrudRepository<LogoutAccessToken,String> {
}
