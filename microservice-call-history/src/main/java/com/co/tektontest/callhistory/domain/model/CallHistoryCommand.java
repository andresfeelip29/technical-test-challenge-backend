package com.co.tektontest.callhistory.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;


/**
 * Domain command history call.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Builder
public record CallHistoryCommand(@NotNull String createAt, @NotNull String pathEndpoint,
                                 @NotNull String methodParameters, @NotNull String response) {
}
