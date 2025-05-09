package com.co.tektontest.calculation.domain.port.in;

import com.co.tektontest.calculation.application.dto.NumbersDataCommand;
import com.co.tektontest.calculation.domain.model.CalculationResult;

import java.util.Optional;

/**
 * Calculate Percentage port interface.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
public interface CalculatePercentagePort {

    /**
     * Method for storing the generated calculation
     *
     * @param dataCommand domain object that stores the calculation data and the result.
     * @return The object is returned with the performed calculations
     */
    Optional<CalculationResult> calculate(NumbersDataCommand dataCommand);

}
