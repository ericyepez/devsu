package com.pe.devsu.util;

public enum ErrorMessage {
    SALDO_NO_DISPONIBLE("Saldo no disponible"),
    CUPO_DIARIO_EXCEDIDO("Cupo diario excedido"),
    OPERACION_NO_RECONOCIDA("No se pudo reconocer la operacion, por favor verifique los datos"),
    ALUMNOS_RETRIEVAL_FAILED("No se pudo eliminar el alumno con ID %s");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}

