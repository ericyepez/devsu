package com.pe.devsu.service.impl;

import com.pe.devsu.exception.CustomException;
import com.pe.devsu.model.Cuenta;
import com.pe.devsu.model.Movimiento;
import com.pe.devsu.repository.CuentaRepository;
import com.pe.devsu.repository.MovimientoRepository;
import com.pe.devsu.service.MovimientoService;
import com.pe.devsu.util.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Value("${movimiento.valor.limite}")
    private double valorLimite;

    @Value("${movimiento.tipo.deposito}")
    private String movimientoDeposito;

    @Value("${movimiento.tipo.retiro}")
    private String movimientoRetiro;

    private final Logger logger = LoggerFactory.getLogger(MovimientoServiceImpl.class);

    @Override
    public Movimiento crearMovimiento(Movimiento movimiento) {
        logger.info("Creando nuevo movimiento: {}", movimiento);
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getNumeroCuenta()).orElse(null);

        if (cuenta != null) {
            String[] vals = movimiento.getDescripcion().split(" ");
            int valorMovimiento = Integer.parseInt(vals[2]);
            String tipoMovimiento = vals[0];
            movimiento.setSaldoInicial(cuenta.getSaldo());
            if (tipoMovimiento.equalsIgnoreCase(movimientoRetiro)) {
                if (cuenta.getSaldo() < valorMovimiento) {
                    throw new CustomException(ErrorMessage.SALDO_NO_DISPONIBLE.getMessage());
                }
                if (valorMovimiento > valorLimite) {
                    throw new CustomException(ErrorMessage.CUPO_DIARIO_EXCEDIDO.getMessage());
                }
                cuenta.setSaldo(cuenta.getSaldo() - valorMovimiento);
            } else if (tipoMovimiento.equalsIgnoreCase(movimientoDeposito)) {
                cuenta.setSaldo(cuenta.getSaldo() + valorMovimiento);
            } else {
                throw new CustomException(ErrorMessage.OPERACION_NO_RECONOCIDA.getMessage());
            }

            cuentaRepository.save(cuenta);
            movimiento.setFecha(new Date());
            movimiento.setValor(valorMovimiento);
            movimiento.setCuenta(cuenta);
            movimiento.setDescripcion(tipoMovimiento);
            return movimientoRepository.save(movimiento);
        }
        return new Movimiento();
    }

    @Override
    public Movimiento actualizarMovimiento(Long id, Movimiento movimientoActualizado) {
        Movimiento movimientoExistente = movimientoRepository.findById(id).orElse(null);
        if (movimientoExistente != null) {
            movimientoExistente.setFecha(movimientoActualizado.getFecha());
            movimientoExistente.setTipoMovimiento(movimientoActualizado.getTipoMovimiento());
            return movimientoRepository.save(movimientoExistente);
        }
        return null;
    }

    @Override
    public void eliminarMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<Movimiento> obtenerMovimientos() {
        return movimientoRepository.findAll();
    }

    @Override
    public Movimiento crearMovimientoPorCuenta(Cuenta cuenta) {
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(new Date());
        movimiento.setDescripcion(movimientoDeposito);
        movimiento.setNumeroCuenta(cuenta.getNumeroCuenta());
        movimiento.setTipoMovimiento(cuenta.getTipoCuenta());
        movimiento.setSaldoInicial(0);
        movimiento.setValor(cuenta.getSaldo());
        movimiento.setCuenta(cuenta);
        return movimientoRepository.save(movimiento);
    }
}
