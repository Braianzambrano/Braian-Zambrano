package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.configuration.MapperConfig;
import com.example.ProyectoIntegrador.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegrador.model.DomicilioDTO;
import com.example.ProyectoIntegrador.model.OdontologoDTO;
import com.example.ProyectoIntegrador.model.PacienteDTO;
import com.example.ProyectoIntegrador.persistence.entities.Domicilio;
import com.example.ProyectoIntegrador.persistence.entities.Odontologo;
import com.example.ProyectoIntegrador.persistence.entities.Paciente;
import com.example.ProyectoIntegrador.persistence.repository.OdontologoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class OdontologoService implements IOdontologoService{

    private static Logger logger = LogManager.getLogger(OdontologoService.class);

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void crearOdontologo(OdontologoDTO odontologoDTO) throws HttpServerErrorException.InternalServerError {
        Odontologo nuevoOdontologo = mapper.map(odontologoDTO, Odontologo.class);
        odontologoRepository.save(nuevoOdontologo);
    }

    @Override
    public Set<OdontologoDTO> listarOdontologos() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDTO> odontologoDTOS = new HashSet<>();
        for(Odontologo odontologo : odontologos){
            odontologoDTOS.add(mapper.map(odontologo, OdontologoDTO.class));
        }
        return odontologoDTOS;
    }

    @Override
    public OdontologoDTO buscarOdontologoPorID(Long id) throws ResourceNotFoundException {
        Odontologo odontologoEncontrado = odontologoRepository.findById(id).orElse(null);
        OdontologoDTO odontologoDTO = null;
        if(odontologoEncontrado != null){
            logger.info("Odontologo encontrado");
            odontologoDTO = mapper.map(odontologoEncontrado, OdontologoDTO.class);

        }else{
                logger.error("ID invalido");
                throw new ResourceNotFoundException("El id ingresado es invalido");
        }

        return odontologoDTO;
    }

    @Override
    public void modificarOdontologo(OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoMod = odontologoRepository.findById(odontologoDTO.getId());
        if(odontologoMod.isPresent()){
            odontologoRepository.save(this.modificarEnBD(odontologoMod.get(), odontologoDTO));
            logger.info("Los datos del odontologo han sido modificados");
        }else {
            throw new ResourceNotFoundException("No se pudieron modificar los datos del odontologo");
        }
    }

    public Odontologo modificarEnBD(Odontologo odontologo, OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        if(odontologoDTO.getNombre()!=null){
            odontologo.setNombre(odontologoDTO.getNombre());
        }
        if(odontologoDTO.getApellido()!=null){
            odontologo.setApellido(odontologoDTO.getApellido());
        }
        if(odontologoDTO.getMatricula()!=null){
            odontologo.setMatricula(odontologoDTO.getMatricula());
        }
        return odontologo;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if(buscarOdontologoPorID(id) != null){
            odontologoRepository.deleteById(id);
            logger.warn("Se ha eliminado el odontologo con id: " , id);
        } else {
            logger.error("No se ha encontrado el odontologo con el id: ", id);
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con el id: " + id);
        }

    }
}


