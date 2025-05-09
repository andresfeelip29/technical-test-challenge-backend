package com.co.tektontest.calculation.infrastructure.shared.exception;

public class ErrorRequestExternalServiceException extends RuntimeException{
    public ErrorRequestExternalServiceException(String message) {
        super(message);
    }
}
