package com.example.ProyectoIntegrador.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomicilioDTO {
    private Long id;
    private String calle;
    private String numero;
    private String ciudad;
    private String provincia;
}
