package com.example.ProyectoIntegrador.service;


import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegrador.model.OdontologoDTO;
import com.example.ProyectoIntegrador.model.PacienteDTO;
import com.example.ProyectoIntegrador.model.TurnoDTO;
import com.example.ProyectoIntegrador.persistence.entities.Paciente;
import com.example.ProyectoIntegrador.persistence.entities.Turno;
import com.example.ProyectoIntegrador.persistence.repository.TurnoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnoService implements ITurnoService{

    private static Logger logger = LogManager.getLogger();
    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void registrarTurno(TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException {
        Turno turnoCreado;
        Turno nuevoTurno = mapper.map(turnoDTO, Turno.class);
        PacienteDTO paciente = pacienteService.buscarPacientePorId(nuevoTurno.getPaciente().getId());
        OdontologoDTO odontologo = odontologoService.buscarOdontologoPorID(nuevoTurno.getOdontologo().getId());
        if(paciente != null && odontologo != null) {
            turnoCreado= turnoRepository.save(nuevoTurno);
            logger.info("Turno registrado con éxito", turnoCreado);
        }else{
            logger.error("No se puede registrar el turno");
            throw new BadRequestException("No se puede registrar el turno");
        }
    }

    @Override
    public Set<TurnoDTO> listarTurnos() throws ResourceNotFoundException {
        List<Turno> turnos = turnoRepository.findAll();
        Set<TurnoDTO> turnoDTOS = new HashSet<>();
        for(Turno turno: turnos){
            turnoDTOS.add(mapper.map(turno, TurnoDTO.class));
        }
        if(turnoDTOS.size() <= 0){
            throw new ResourceNotFoundException("No hay turnos cargados");
        }
        return turnoDTOS;
    }

    @Override
    public Set<TurnoDTO> buscarTurnoPorPaciente(Long idPaciente) throws ResourceNotFoundException {
        Set<Turno> turnos = turnoRepository.findByPaciente(idPaciente);
        Set<TurnoDTO> turnosEncontrados = new HashSet<>();
        for(Turno turno : turnos){
            turnosEncontrados.add(mapper.map(turno, TurnoDTO.class));
        }
        if(turnosEncontrados.size() <= 0){
            logger.error("El paciente no posee turnos");
            throw new ResourceNotFoundException("El paciente no posee turnos");
        }
        else{
            logger.info("Turnos encontrados: ", turnosEncontrados);
        }

        return turnosEncontrados;
    }


    @Override
    public TurnoDTO buscarTurnoPorFecha(LocalDateTime fecha) throws ResourceNotFoundException {
        Optional<Turno> turnoEncontrado = turnoRepository.findAll().stream().filter(turno -> turno.getFechaDeCita().equals(fecha)).findFirst();
        if (turnoEncontrado.isPresent()) {
            Turno turno = turnoEncontrado.get();
            return mapper.map(turno, TurnoDTO.class);
        } else {
            throw new ResourceNotFoundException("No se encontro ningun turno");
        }
    }


}
