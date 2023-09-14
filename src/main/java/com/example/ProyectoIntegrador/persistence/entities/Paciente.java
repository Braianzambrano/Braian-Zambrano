package com.example.ProyectoIntegrador.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "fecha de alta", nullable = false)
    private LocalDate fechaDeAlta;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id", nullable = false)
    private Domicilio domicilio;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Paciente(){

    }

    public Paciente(String nombre, String apellido, String dni, LocalDate fechaDeAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeAlta = fechaDeAlta;
    }
}
