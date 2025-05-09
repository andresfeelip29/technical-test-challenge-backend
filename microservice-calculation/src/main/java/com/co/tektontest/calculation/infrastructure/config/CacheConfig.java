package com.co.tektontest.calculation.infrastructure.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Configuration class for the local cache handler.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Configuration
@EnableCaching
public class CacheConfig {

    private final CacheConfigData cacheConfigData;

    public CacheConfig(CacheConfigData cacheConfigData) {
        this.cacheConfigData = cacheConfigData;
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(cacheConfigData.getCacheConfigName());
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(cacheConfigData.getTimeExpire(), TimeUnit.MINUTES)
                .maximumSize(cacheConfigData.getMaximumSize())
                .recordStats();
    }

}
