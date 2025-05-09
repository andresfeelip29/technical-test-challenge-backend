package com.co.tektontest.calculation.infrastructure.shared.enums;

public enum ExceptionMessage {

    PERCENTAGE_NOT_FOUND_EXTERNAL_REQUEST("No existe porcentaje almacenado en servicio externo!"),
    ERROR_REQUEST_EXTERNAL_REQUEST("Se presento un error al hacer la consulta en servicio externo!"),
    ERROR_CALCULATION_PERCENTAGE("Error al calcular porcentaje, no se encontro en servicio externo ni en cache!");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
