package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.configuration.MapperConfig;
import com.example.ProyectoIntegrador.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegrador.model.DomicilioDTO;
import com.example.ProyectoIntegrador.persistence.entities.Domicilio;
import com.example.ProyectoIntegrador.persistence.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DomicilioService {

    @Autowired
    DomicilioRepository repository;

    @Autowired
    MapperConfig mapper;

    public DomicilioDTO actualizar(DomicilioDTO domicilioDto) throws ResourceNotFoundException {
        Optional<Domicilio> domicilioEnBD = repository.findById(domicilioDto.getId());
        if (domicilioEnBD.isPresent()) {
            Domicilio actualizado = this.actualizar(domicilioEnBD.get(), domicilioDto);
            Domicilio guardado = repository.save(actualizado);
            return mapper.getModelMapper().map(guardado, DomicilioDTO.class);
        }
        else {
            throw new ResourceNotFoundException("El domicilio con id " + domicilioDto.getId() +" no fue encontrado en la base de datos");
        }
    }

    private Domicilio actualizar(Domicilio domicilio, DomicilioDTO domicilioDto) {
        if (domicilioDto.getCalle() != null) {
            domicilio.setCalle(domicilioDto.getCalle());
        }
        if (domicilioDto.getNumero() != null) {
            domicilio.setNumero(domicilioDto.getNumero());
        }
        if (domicilioDto.getCiudad() != null) {
            domicilio.setCiudad(domicilioDto.getCiudad());
        }
        if (domicilioDto.getProvincia() != null) {
            domicilio.setProvincia(domicilioDto.getProvincia());
        }
        return domicilio;
    }
}
