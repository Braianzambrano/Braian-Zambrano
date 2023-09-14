package com.example.ProyectoIntegrador.persistence.repository;

import com.example.ProyectoIntegrador.persistence.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
