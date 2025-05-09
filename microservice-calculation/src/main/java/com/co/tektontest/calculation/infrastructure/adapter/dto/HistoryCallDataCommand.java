package com.co.tektontest.calculation.infrastructure.adapter.dto;

import lombok.Builder;

/**
 * Data transfer object for send information to msvc history call.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Builder
public record HistoryCallDataCommand(String createAt, String pathEndpoint, String methodParameters, String response) {
}
