package com.pe.devsu.mapper;

import com.pe.devsu.dto.ClienteDTO;
import com.pe.devsu.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO mapToDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setNombre(cliente.getNombre());
        dto.setIdentificacion(cliente.getIdentificacion());
        return dto;
    }

    public Cliente dtoToObject(ClienteDTO dtoCliente) {
        Cliente cliente = new Cliente();
        cliente.setContrasena(dtoCliente.getContrasena());
        cliente.setEstado(dtoCliente.isEstado());
        cliente.setNombre(dtoCliente.getNombre());
        cliente.setGenero(dtoCliente.getGenero());
        cliente.setEdad(dtoCliente.getEdad());
        cliente.setDireccion(dtoCliente.getDireccion());
        cliente.setTelefono(dtoCliente.getTelefono());
        cliente.setIdentificacion(dtoCliente.getIdentificacion());
        return cliente;
    }
}
