package com.co.tektontest.calculation.domain.mapper;

import com.co.tektontest.calculation.application.dto.NumbersDataCommand;
import com.co.tektontest.calculation.domain.model.CalculationResult;
import org.springframework.stereotype.Component;


/**
 * Mapping class for the calculation of the procentajerealziado.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Component
public class CalculationPercentageDomainMapper {

    public CalculationResult dataCommanToCalculationResult(NumbersDataCommand dataCommand,
                                                           Double percentage,
                                                           Double result) {
        return new CalculationResult(dataCommand.firstData(),
                dataCommand.secondData(), percentage, result);

    }
}
