package com.pe.devsu.controller;

import com.pe.devsu.dto.ClienteDTO;
import com.pe.devsu.mapper.ClienteMapper;
import com.pe.devsu.model.Cliente;
import com.pe.devsu.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteService.obtenerClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(id);
        if (cliente.isPresent()) {
            ClienteDTO clienteDTO = clienteMapper.mapToDTO(cliente.get());
            return ResponseEntity.ok(clienteDTO); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente nuevoCliente = clienteService.crearCliente(clienteMapper.dtoToObject(clienteDTO));
            clienteDTO.setId(nuevoCliente.getId());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(clienteDTO); // 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarClientePorId(id);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id,
                                     @RequestBody Cliente clienteActualizado) {
        return clienteService.actualizarCliente(id, clienteActualizado);
    }
}
