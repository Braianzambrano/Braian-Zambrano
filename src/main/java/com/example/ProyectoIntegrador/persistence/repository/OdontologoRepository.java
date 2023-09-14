package com.example.ProyectoIntegrador.persistence.repository;

import com.example.ProyectoIntegrador.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {

}
