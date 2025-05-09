package com.co.tektontest.callhistory.infrastructure.shared.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(Integer code, String message) { }
