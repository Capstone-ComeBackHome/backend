package com.comebackhome.authentication.infrastructure.repository;

import com.comebackhome.authentication.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken,String> {
}
