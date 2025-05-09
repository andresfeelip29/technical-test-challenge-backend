package com.co.tektontest.calculation.application.dto;


import lombok.Builder;

/**
 * Data transfer object for the calculation.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Builder
public record NumbersDataCommand(Double firstData, Double secondData) { }
