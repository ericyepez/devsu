package com.pe.devsu.dto;

import lombok.Data;

@Data
public class ReporteDTO {

    private String fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private int saldoInicial;
    private int movimiento;
    private int saldoDisponible;
    private String tipoMovimiento;
    private boolean estado;
}
