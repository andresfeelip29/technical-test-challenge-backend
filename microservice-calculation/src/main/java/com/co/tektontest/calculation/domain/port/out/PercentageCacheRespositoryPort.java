package com.co.tektontest.calculation.domain.port.out;

/**
 * Repository interface.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
public interface PercentageCacheRespositoryPort {

    /**
     * Method for storing the generated calculation
     * @param key cached message identifier.
     * @param percentage domain object that stores the calculation data and the result.
     * @return Returns the same object stored
     */
    Double savePercentageCache(String key, Double percentage);

    /**
     * Method for storing the generated calculation
     * @param key cached message identifier.
     * @return Returns the same object stored
     */
    Double getPercentageCache(String key);

}
