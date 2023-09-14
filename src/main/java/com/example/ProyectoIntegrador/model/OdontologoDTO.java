package com.example.ProyectoIntegrador.model;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OdontologoDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;


}