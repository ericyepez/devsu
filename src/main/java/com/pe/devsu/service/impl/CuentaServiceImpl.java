package com.pe.devsu.service.impl;

import com.pe.devsu.model.Cliente;
import com.pe.devsu.model.Cuenta;
import com.pe.devsu.repository.ClienteRepository;
import com.pe.devsu.repository.CuentaRepository;
import com.pe.devsu.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Cuenta crearCuenta(Cuenta cuenta) {
        Optional<Cliente> cliente = clienteRepository.findById(cuenta.getCliente().getId());
        cliente.ifPresent(cliente1 -> clienteRepository.save(cliente1));
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada) {
        Cuenta cuentaExistente = cuentaRepository.findById(id).orElse(null);
        if (cuentaExistente != null) {
            cuentaExistente.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
            cuentaExistente.setTipoCuenta(cuentaActualizada.getTipoCuenta());
            return cuentaRepository.save(cuentaExistente);
        }
        return null;
    }

    @Override
    public void eliminarCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public List<Cuenta> obtenerCuentas() {
        return cuentaRepository.findAll();
    }

}

