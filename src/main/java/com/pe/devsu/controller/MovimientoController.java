package com.pe.devsu.controller;

import com.pe.devsu.dto.MovimientoDTO;
import com.pe.devsu.exception.CustomException;
import com.pe.devsu.mapper.MovimientoMapper;
import com.pe.devsu.model.Movimiento;
import com.pe.devsu.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private MovimientoMapper movimientoMapper;

    @GetMapping
    public List<Movimiento> obtenerMovimientos() {
        return movimientoService.obtenerMovimientos();
    }

    @PostMapping
    public ResponseEntity<MovimientoDTO> crearMovimiento(@RequestBody Movimiento movimiento) {
        try {
            Movimiento nuevoMovimiento = movimientoService.crearMovimiento(movimiento);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(movimientoMapper.mapToDTO(nuevoMovimiento)); // 201 Created
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Movimiento actualizarMovimiento(@PathVariable Long id, @RequestBody Movimiento movimientoActualizado) {
        return movimientoService.actualizarMovimiento(id, movimientoActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarMovimiento(@PathVariable Long id) {
        movimientoService.eliminarMovimiento(id);
    }
}
