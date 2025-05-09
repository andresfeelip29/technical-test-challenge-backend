package com.co.tektontest.callhistory.domain.model;

import lombok.Builder;

/**
 * Domain response history call.
 *
 * @author andres on 2025/05/07.
 * @version 1.0.0
 */
@Builder
public record CallHistoryResponse(Long id, String createAt, String pathEndpoint,
                                  String methodParameters, String response) {
}
