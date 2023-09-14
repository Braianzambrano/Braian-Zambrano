package com.example.ProyectoIntegrador.persistence.repository;

import com.example.ProyectoIntegrador.persistence.entities.Odontologo;
import com.example.ProyectoIntegrador.persistence.entities.Paciente;
import com.example.ProyectoIntegrador.persistence.entities.Turno;
import com.example.ProyectoIntegrador.service.PacienteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long>{

    Set<Turno> findByPaciente(Long idPaciente);



}
