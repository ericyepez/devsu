package com.pe.devsu.service;

import com.pe.devsu.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> obtenerClientes();
    Optional<Cliente> obtenerClientePorId(Long id);
    Cliente crearCliente(Cliente cliente);
    void eliminarClientePorId(Long id);
    Cliente actualizarCliente(Long id, Cliente clienteActualizado);
}
