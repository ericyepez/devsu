package com.pe.devsu.controller;

import com.pe.devsu.dto.CuentaDTO;
import com.pe.devsu.exception.CustomException;
import com.pe.devsu.mapper.CuentaMapper;
import com.pe.devsu.model.Cuenta;
import com.pe.devsu.service.CuentaService;
import com.pe.devsu.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<Cuenta> obtenerCuentas() {
        return cuentaService.obtenerCuentas();
    }

    @PostMapping
    public ResponseEntity<CuentaDTO> crearCuenta(@RequestBody Cuenta cuenta) {
        try {
            Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta);
            movimientoService.crearMovimientoPorCuenta(nuevaCuenta);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(cuentaMapper.mapToDTO(nuevaCuenta)); // 201 Created
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Cuenta actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaActualizada) {
        return cuentaService.actualizarCuenta(id, cuentaActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
    }
}

