package com.example.ProyectoIntegrador.model;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class TurnoDTO {

    private String id;
    private LocalDateTime fechaCita;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;


}
