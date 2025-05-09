package com.co.tektontest.calculation.infrastructure.shared.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(Integer code, String message) {
}
