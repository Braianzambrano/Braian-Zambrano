package com.example.ProyectoIntegrador.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
@Getter
@Setter
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    @SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fechaDeCita;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id", nullable = false)
    private Odontologo odontologo;

    public Turno(){

    }

    public Turno(LocalDateTime fechaDeCita, Paciente paciente, Odontologo odontologo) {
        this.fechaDeCita = fechaDeCita;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }
}
