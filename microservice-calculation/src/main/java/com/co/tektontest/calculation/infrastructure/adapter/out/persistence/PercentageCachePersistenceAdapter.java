package com.co.tektontest.calculation.infrastructure.adapter.out.persistence;

import com.co.tektontest.calculation.domain.port.out.PercentageCacheRespositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


/**
 * implementation of the repository to store in memory using the aside cache pattern.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Component
@Slf4j
public class PercentageCachePersistenceAdapter implements PercentageCacheRespositoryPort {

    /**
     * {@inheritDoc}
     */
    @Override
    @CachePut(value = "calculate", key = "#key")
    public Double savePercentageCache(String key, Double percentage) {
        return percentage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(value = "calculate", key = "#key")
    public Double getPercentageCache(String key) {
        return null;
    }
}
