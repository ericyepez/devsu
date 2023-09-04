package com.pe.devsu.controller;

import com.pe.devsu.dto.ReporteDTO;
import com.pe.devsu.service.ReporteService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reportes")
@Validated
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/obtener-reporte")
    public List<ReporteDTO> obtenerReporte(
            @RequestParam @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") @PastOrPresent Date fechaInicio,
            @RequestParam @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin,
            @RequestParam @NotNull @Positive Long idCliente) {
        return reporteService.obtenerReporte(fechaInicio, fechaFin, idCliente);
    }
}
