package com.pe.devsu.service.impl;

import com.pe.devsu.model.Cliente;
import com.pe.devsu.model.Persona;
import com.pe.devsu.repository.ClienteRepository;
import com.pe.devsu.repository.PersonaRepository;
import com.pe.devsu.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    private final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Override
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        logger.info("Creando nuevo cliente: {}", cliente);
        Persona persona = cliente;
        personaRepository.save(persona);
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarClientePorId(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
        if (clienteExistente != null) {
            clienteExistente.setNombre(clienteActualizado.getNombre());
            clienteExistente.setGenero(clienteActualizado.getGenero());
            // Actualiza otros campos según sea necesario
            return clienteRepository.save(clienteExistente);
        }
        return null; // O podrías lanzar una excepción si el cliente no se encuentra
    }
}
