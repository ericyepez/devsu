package com.pe.devsu.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ClienteDTO {

    private long id;
    @NotBlank
    private String nombre;
    @NotNull(message = "El campo nombre no puede ser nulo")
    private String contrasena;
    @NotBlank
    private String genero;
    @AssertTrue(message = "El valor debe ser verdadero")
    private boolean estado;
    //@Min(1) @Max(2)
    private int edad;
    @NotBlank
    private String direccion;
    @NotBlank
    private String telefono;
    @NotBlank
    private String identificacion;

}
