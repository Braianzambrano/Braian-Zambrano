package com.example.ProyectoIntegrador.persistence.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "domicilios")
@Getter
@Setter
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    @SequenceGenerator(name = "turno_sequence", sequenceName = "turno_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "calle", nullable = false)
    private String calle;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "provincia", nullable = false)
    private String provincia;

    public Domicilio(){

    }

    public Domicilio(String calle, String numero, String ciudad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.provincia = provincia;
    }
}

