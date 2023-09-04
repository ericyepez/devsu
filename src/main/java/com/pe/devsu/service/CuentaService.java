package com.pe.devsu.service;

import com.pe.devsu.model.Cuenta;

import java.util.List;

public interface CuentaService {
    Cuenta crearCuenta(Cuenta cuenta);
    Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada);
    void eliminarCuenta(Long id);
    List<Cuenta> obtenerCuentas();
}
