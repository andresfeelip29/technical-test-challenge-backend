package com.co.tektontest.calculation.infrastructure.adapter.in.rest;

import com.co.tektontest.calculation.application.dto.NumbersDataCommand;
import com.co.tektontest.calculation.domain.model.CalculationResult;
import com.co.tektontest.calculation.domain.port.in.CalculatePercentagePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for percentage calculation.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/calculate")
public class CalculatePercentageController {

    private final CalculatePercentagePort calculatePercentagePort;

    public CalculatePercentageController(CalculatePercentagePort calculatePercentagePort) {
        this.calculatePercentagePort = calculatePercentagePort;
    }

    /**
     * {@code POST /} : Request calculate data
     *
     * Endpoint to calculate the percentage of two numbers.
     *
     * @param firstData  first number parameter .
     * @param secondData second number parameter .
     * @return {@link ResponseEntity<CalculationResult>} Object of response where the information and result of the calculation is located.
     */
    @Operation(
            summary = "Calculate percentage",
            description = "Endpoint to calculate the percentage of two numbers",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Calculation successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the calculation!"),
                    @ApiResponse(responseCode = "412", description = "EError in the validation of the sent parameters!")
            }
    )
    @PostMapping("/")
    public ResponseEntity<CalculationResult> calculatePercentage(
            @RequestParam(name = "firstData") @NotNull Double firstData,
            @RequestParam(name = "secondData") @NotNull Double secondData
    ) {

        log.info("Se recibe peticion para realizar calculo con parametros: {} , {}!", firstData, secondData);

        NumbersDataCommand dataCommand =
                new NumbersDataCommand(firstData, secondData);

        return this.calculatePercentagePort.calculate(dataCommand)
                .map(result -> ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result))
                .orElse(ResponseEntity.badRequest().build());

    }

}
