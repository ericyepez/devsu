package com.pe.devsu.exception;

import lombok.Data;
import java.util.Date;

@Data
public class ErrorResponse {

    private Date timestamp;
    private String mensaje;
    private String detalles;
    private String httpCodeMessage;

    public ErrorResponse(Date timestamp, String message, String details,String httpCodeMessage) {
        super();
        this.timestamp = timestamp;
        this.mensaje = message;
        this.detalles = details;
        this.httpCodeMessage=httpCodeMessage;
    }
}
