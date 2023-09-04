package com.pe.devsu.mapper;

import com.pe.devsu.dto.MovimientoDTO;
import com.pe.devsu.model.Movimiento;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {

    public MovimientoDTO mapToDTO(Movimiento movimiento) {
        MovimientoDTO dto = new MovimientoDTO();
        dto.setId(movimiento.getId());
        dto.setFecha(movimiento.getFecha());
        dto.setTipoMovimiento(movimiento.getDescripcion());
        dto.setValor(movimiento.getValor());
        dto.setNumeroCuenta(movimiento.getNumeroCuenta());
        return dto;
    }
}
