package com.example.ProyectoIntegrador.persistence.entities;


import com.example.ProyectoIntegrador.model.OdontologoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "odontologos")
@Getter
@Setter
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
    @SequenceGenerator(name = "odontologo_sequence", sequenceName = "odontologo_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "matricula", nullable = false)
    private String matricula;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Paciente> pacientes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Odontologo(){
 
    }

    public Odontologo(String nombre, String apellido, String matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }
}
