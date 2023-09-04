package com.pe.devsu.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MovimientoDTO {

    private Long id;
    private Date fecha;
    private String tipoMovimiento;
    private int valor;
    private String numeroCuenta;
}
