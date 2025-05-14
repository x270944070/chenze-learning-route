package com.chenze.technicalcase.user.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    private static final String REDIS_PROTOCOL_PREFIX = "redis://";

    private final RedisProperties redisProperties;

    public RedissonConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.setCheckLockSyncedSlaves(false);

        int max = redisProperties.getLettuce().getPool().getMaxActive();
        int min = redisProperties.getLettuce().getPool().getMinIdle();

        SingleServerConfig singleConfig = config.useSingleServer()
                .setAddress(REDIS_PROTOCOL_PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setDatabase(redisProperties.getDatabase())
                .setPassword(redisProperties.getPassword())
                .setConnectionPoolSize(max)
                .setConnectionMinimumIdleSize(min);

        if (redisProperties.getTimeout() != null) {
            singleConfig.setConnectTimeout((int) redisProperties.getTimeout().toMillis());
        }

        return Redisson.create(config);
    }
}
