package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegrador.model.DomicilioDTO;
import com.example.ProyectoIntegrador.model.PacienteDTO;
import com.example.ProyectoIntegrador.persistence.entities.Domicilio;
import com.example.ProyectoIntegrador.persistence.entities.Paciente;
import com.example.ProyectoIntegrador.persistence.repository.PacienteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class PacienteService implements IPacienteService{

    private static Logger logger = LogManager.getLogger(PacienteService.class);

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DomicilioService domicilioService;

    @Override
    public void crearPaciente(PacienteDTO pacienteDTO) {
        Paciente nuevoPaciente = mapper.map(pacienteDTO, Paciente.class);
        pacienteRepository.save(nuevoPaciente);
    }

    @Override
    public Set<PacienteDTO> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        Set<PacienteDTO> pacienteDTOS = new HashSet<>();
        for(Paciente paciente : pacientes){
            pacienteDTOS.add(mapper.map(paciente, PacienteDTO.class));
        }
        logger.info("Listado de pacientes", pacienteDTOS);
        return pacienteDTOS;
    }

    @Override
    public PacienteDTO buscarPacientePorId(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
        PacienteDTO pacienteDTO = null;
        if(pacienteEncontrado == null || pacienteEncontrado.isEmpty()){
            logger.error("ID invalido");
            throw new ResourceNotFoundException("El id ingresado es invalido");
        }else{
            if(pacienteEncontrado.isPresent()){
                pacienteDTO = mapper.map(pacienteEncontrado, PacienteDTO.class);
            }
        }
        return pacienteDTO;
    }

    @Override
    public void modificarPaciente(PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        Optional<Paciente> pacienteMod = pacienteRepository.findById(pacienteDTO.getId());
        if(pacienteMod.isPresent()){
            pacienteRepository.save(this.modificarEnBD(pacienteMod.get(), pacienteDTO));
            logger.info("Los datos del paciente han sido modificados");
        }else {
            throw new ResourceNotFoundException("No se pudieron modificar los datos del paciente");
        }
    }

    public Paciente modificarEnBD(Paciente paciente, PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        if(pacienteDTO.getNombre()!=null){
            paciente.setNombre(pacienteDTO.getNombre());
        }
        if(pacienteDTO.getApellido()!=null){
            paciente.setApellido(pacienteDTO.getApellido());
        }
        if(pacienteDTO.getDni()!=null){
            paciente.setDni(pacienteDTO.getDni());
        }
        if(pacienteDTO.getDomicilio()!=null){
            DomicilioDTO domicilioActualizado = domicilioService.actualizar(pacienteDTO.getDomicilio());
            paciente.setDomicilio(mapper.map(domicilioActualizado, Domicilio.class));
        }
        return paciente;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if(buscarPacientePorId(id) != null){
            pacienteRepository.deleteById(id);
            logger.warn("Se ha eliminado el paciente con el id: ", id);
        }else{
            logger.error("No se ha encontrado el paciente con el id: ", id);
            throw new ResourceNotFoundException("No se ha encontrado el paciente con el id: " + id);
        }

    }


}
