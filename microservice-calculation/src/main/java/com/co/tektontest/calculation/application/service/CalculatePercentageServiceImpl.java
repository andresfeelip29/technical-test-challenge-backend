package com.co.tektontest.calculation.application.service;

import com.co.tektontest.calculation.application.dto.NumbersDataCommand;
import com.co.tektontest.calculation.application.exception.ErrorCalculatePercentageException;
import com.co.tektontest.calculation.application.exception.PercentageNotFoundException;
import com.co.tektontest.calculation.domain.mapper.CalculationPercentageDomainMapper;
import com.co.tektontest.calculation.domain.model.CalculationResult;
import com.co.tektontest.calculation.domain.port.in.CalculatePercentagePort;
import com.co.tektontest.calculation.domain.port.out.PercentageCacheRespositoryPort;
import com.co.tektontest.calculation.domain.port.out.ExternalServicePort;
import com.co.tektontest.calculation.infrastructure.shared.enums.ExceptionMessage;
import com.co.tektontest.calculation.infrastructure.shared.exception.ErrorRequestExternalServiceException;
import com.co.tektontest.calculation.infrastructure.shared.exception.PercentegaExternalRquestNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


/**
 * Service implementing the port for percentage calculation.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Service
@Slf4j
public class CalculatePercentageServiceImpl implements CalculatePercentagePort {

    private final PercentageCacheRespositoryPort percentageCacheRespositoryPort;
    private final ExternalServicePort externalServicePort;
    private final CalculationPercentageDomainMapper calculationPercentageDomainMapper;
    @Value("${config-cache.cache-config-key}")
    private String keyValue;

    public CalculatePercentageServiceImpl(PercentageCacheRespositoryPort percentageCacheRespositoryPort,
                                          ExternalServicePort externalServicePort,
                                          CalculationPercentageDomainMapper calculationPercentageDomainMapper) {
        this.percentageCacheRespositoryPort = percentageCacheRespositoryPort;
        this.externalServicePort = externalServicePort;
        this.calculationPercentageDomainMapper = calculationPercentageDomainMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<CalculationResult> calculate(NumbersDataCommand dataCommand) {

        Double percentage = null;

        try {

            log.info("Se realiza peticion a servicio externo para obtener porcentaje!");
            percentage = this.externalServicePort.getExternalPercentage();

        } catch (PercentegaExternalRquestNotFoundException | ErrorRequestExternalServiceException e) {

            log.info("Error en consulta a servicio externo, se procede a consulta en cache del porcentaje");
            percentage = this.percentageCacheRespositoryPort.getPercentageCache(keyValue);

        }

        if (Objects.isNull(percentage)) {

            log.info("Error en consulta de percentaje en cache, no se encontro dato almacenado!");
            throw new ErrorCalculatePercentageException(
                    String.format(ExceptionMessage.ERROR_CALCULATION_PERCENTAGE.getMessage()));

        } else {
            log.info("Se procede a calcular con porcentaje: {}", percentage);
            Double result = operationWithPercentage(dataCommand, percentage);
            this.percentageCacheRespositoryPort.savePercentageCache(keyValue, percentage);
            return Optional.of(
                    this.calculationPercentageDomainMapper.dataCommanToCalculationResult(dataCommand, percentage, result));
        }
    }


    /**
     * Method for storing the generated calculation
     *
     * @param dataCommand Object where the parameters for the calculation are located.
     * @param percentage  percentage to perform the calculation.
     * @return The final value is returned with the percentage calculation.
     */
    private Double operationWithPercentage(NumbersDataCommand dataCommand, Double percentage) {
        Double sumValue = dataCommand.firstData() + dataCommand.secondData();
        Double percentageValue = sumValue * percentage;
        return sumValue + percentageValue;
    }
}
