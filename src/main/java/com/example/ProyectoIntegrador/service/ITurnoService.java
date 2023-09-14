package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegrador.model.TurnoDTO;

import java.time.LocalDateTime;
import java.util.Set;

public interface ITurnoService {

    void registrarTurno(TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException;

    Set<TurnoDTO> listarTurnos() throws ResourceNotFoundException;

    Set<TurnoDTO> buscarTurnoPorPaciente(Long idPaciente) throws ResourceNotFoundException;

    TurnoDTO buscarTurnoPorFecha(LocalDateTime fecha) throws ResourceNotFoundException;




}
