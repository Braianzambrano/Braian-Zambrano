package com.example.ProyectoIntegrador.model;



import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;


@Getter
@Setter
public class PacienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaAlta;
    private DomicilioDTO domicilio;


}
