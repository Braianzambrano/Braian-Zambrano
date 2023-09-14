package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegrador.model.PacienteDTO;

import java.util.Set;

public interface IPacienteService {

    void crearPaciente(PacienteDTO pacienteDTO);
    Set<PacienteDTO> listarPacientes();

    PacienteDTO buscarPacientePorId(Long id) throws ResourceNotFoundException;

    void modificarPaciente(PacienteDTO pacienteDTO) throws ResourceNotFoundException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
