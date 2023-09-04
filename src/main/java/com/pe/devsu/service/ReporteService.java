package com.pe.devsu.service;

import com.pe.devsu.dto.ReporteDTO;

import java.util.Date;
import java.util.List;

public interface ReporteService {
    List<ReporteDTO> obtenerReporte(Date fechaInicio, Date fechaFin, Long idCliente);
}
