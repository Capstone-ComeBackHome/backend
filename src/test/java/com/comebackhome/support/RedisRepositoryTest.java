package com.comebackhome.support;

import com.comebackhome.config.EmbeddedRedisConfig;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;

@Import({EmbeddedRedisConfig.class})
@DataRedisTest
public class RedisRepositoryTest {
}
