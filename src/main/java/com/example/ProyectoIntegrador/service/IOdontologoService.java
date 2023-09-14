package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegrador.model.OdontologoDTO;
import com.example.ProyectoIntegrador.model.PacienteDTO;
import org.springframework.web.client.HttpServerErrorException;


import java.util.Set;

public interface IOdontologoService{
    void crearOdontologo(OdontologoDTO odontologoDTO) throws HttpServerErrorException.InternalServerError;
    Set<OdontologoDTO> listarOdontologos();
    OdontologoDTO buscarOdontologoPorID(Long id) throws ResourceNotFoundException;
    void modificarOdontologo(OdontologoDTO odontologoDTO) throws ResourceNotFoundException;
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

}
