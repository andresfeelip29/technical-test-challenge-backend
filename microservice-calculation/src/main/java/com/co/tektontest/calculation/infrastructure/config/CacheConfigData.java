package com.co.tektontest.calculation.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Data config for cache caffeine.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "config-cache")
public class CacheConfigData {
    private Integer timeExpire;
    private Integer maximumSize;
    private String cacheConfigName;
    private String cacheConfigKey;
}
