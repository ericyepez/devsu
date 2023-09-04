package com.pe.devsu.service.impl;

import com.pe.devsu.dto.ReporteDTO;
import com.pe.devsu.model.Cuenta;
import com.pe.devsu.model.Movimiento;
import com.pe.devsu.repository.CuentaRepository;
import com.pe.devsu.repository.MovimientoRepository;
import com.pe.devsu.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Value("${movimiento.tipo.deposito}")
    private String movimientoDeposito;

    @Override
    public List<ReporteDTO> obtenerReporte(Date fechaInicio,
                                           Date fechaFin,
                                           Long idCliente) {
        List<ReporteDTO> reporteDTOList = new ArrayList<>();
        List<Cuenta> listCuenta = cuentaRepository.findAllByIdCliente(idCliente);
        listCuenta.stream().forEach(cuenta -> {
            List<Movimiento> movimientoList = movimientoRepository.findAllByCuentaId(cuenta.getId());
            movimientoList.stream().forEach(movimiento -> {
                ReporteDTO reporteDTO = new ReporteDTO();
                Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                reporteDTO.setFecha(formatter.format(movimiento.getFecha()));
                reporteDTO.setCliente(cuenta.getCliente().getNombre());
                reporteDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
                reporteDTO.setTipoCuenta(cuenta.getTipoCuenta());
                reporteDTO.setTipoMovimiento(movimiento.getDescripcion());
                reporteDTO.setSaldoInicial(movimiento.getSaldoInicial());
                reporteDTO.setEstado(cuenta.getEstado());
                reporteDTO.setMovimiento(movimiento.getValor());
                reporteDTO.setSaldoDisponible(movimiento.getSaldoInicial() +
                        (movimiento.getDescripcion().equalsIgnoreCase(movimientoDeposito) ?
                        movimiento.getValor() : -movimiento.getValor()));
                reporteDTOList.add(reporteDTO);
            });
        });
        return reporteDTOList;
    }
}
