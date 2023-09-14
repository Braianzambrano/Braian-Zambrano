package com.example.ProyectoIntegrador.persistence.repository;

import com.example.ProyectoIntegrador.persistence.entities.Odontologo;
import com.example.ProyectoIntegrador.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {

}
