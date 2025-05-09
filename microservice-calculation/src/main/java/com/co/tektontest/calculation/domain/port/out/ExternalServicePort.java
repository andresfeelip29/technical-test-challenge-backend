package com.co.tektontest.calculation.domain.port.out;

import com.co.tektontest.calculation.infrastructure.adapter.dto.HistoryCallDataCommand;

/**
 * External service port interface.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
public interface ExternalServicePort {


    /**
     * Method for outpatient consultation of the percentage to be calculated.
     * @return returns the percentage of type double.
     */
    public Double getExternalPercentage();

    /**
     * Method for outpatient consultation of the percentage to be calculated.
     * @return returns the percentage of type double.
     */
    public void sendExternalCallHistory(HistoryCallDataCommand callDataCommand) throws InterruptedException;
}
