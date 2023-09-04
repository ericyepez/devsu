package com.pe.devsu.service.impl;

import com.pe.devsu.model.Cliente;
import com.pe.devsu.model.Cuenta;
import com.pe.devsu.repository.ClienteRepository;
import com.pe.devsu.service.CuentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class CuentaServiceImplTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaService cuentaService;

    @Test
    @Rollback(false) // Evita el rollback automático de la transacción
    void testCrearCuenta() {
        // Crea un cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Nombre del Cliente");
        cliente.setContrasena("contraseña");
        cliente.setEstado(true);
        clienteRepository.save(cliente);

        // Crea una cuenta asociada al cliente
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456789");
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldo(1000);
        cuenta.setEstado(true);
        cuenta.setCliente(cliente);

        // Llama al método crearCuenta
        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta);

        // Verifica que la cuenta se haya guardado correctamente
        assertTrue(nuevaCuenta.getId() > 0); // Asegura que se haya asignado un ID
        assertEquals("123456789", nuevaCuenta.getNumeroCuenta());
        assertEquals("Ahorro", nuevaCuenta.getTipoCuenta());
        assertEquals(1000, nuevaCuenta.getSaldo());
        assertEquals(true, nuevaCuenta.getEstado());
        assertEquals(cliente.getId(), nuevaCuenta.getCliente().getId());
    }
}
