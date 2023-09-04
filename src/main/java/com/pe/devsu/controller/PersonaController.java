package com.pe.devsu.controller;

import com.pe.devsu.model.Persona;
import com.pe.devsu.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> obtenerTodasLasPersonas() {
        return personaService.findAll();
    }

    @GetMapping("/{id}")
    public Persona obtenerPersonaPorId(@PathVariable Long id) {
        return personaService.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Persona actualizarPersona(@PathVariable Long id, @RequestBody Persona personaActualizada) {
        Persona personaExistente = personaService.findById(id).orElse(null);
        if (personaExistente != null) {
            personaExistente.setNombre(personaActualizada.getNombre());
            return personaService.save(personaExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminarPersona(@PathVariable Long id) {
        personaService.deleteById(id);
    }
}
