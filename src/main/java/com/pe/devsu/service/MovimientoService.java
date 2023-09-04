package com.pe.devsu.service;

import com.pe.devsu.model.Cuenta;
import com.pe.devsu.model.Movimiento;

import java.util.List;

public interface MovimientoService {
    Movimiento crearMovimiento(Movimiento movimiento);
    Movimiento actualizarMovimiento(Long id, Movimiento movimientoActualizado);
    void eliminarMovimiento(Long id);
    List<Movimiento> obtenerMovimientos();
    Movimiento crearMovimientoPorCuenta(Cuenta cuenta);
}
