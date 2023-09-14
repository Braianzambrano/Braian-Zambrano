package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegrador.model.PacienteDTO;
import com.example.ProyectoIntegrador.model.TurnoDTO;
import com.example.ProyectoIntegrador.persistence.entities.Turno;
import com.example.ProyectoIntegrador.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private ITurnoService turnoService;

    @PostMapping("/registrarTurno")
    public ResponseEntity<String> registrarTurno(@RequestBody TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<String> respuesta = null;
        turnoService.registrarTurno(turnoDTO);
        respuesta = ResponseEntity.status(HttpStatus.OK).body("Turno registrado con éxito");
        return respuesta;
    }

    @GetMapping("/buscarPorPaciente/{idPaciente}")
    public ResponseEntity<String> buscarTurnoPorPaciente(@PathVariable Long idPaciente) throws ResourceNotFoundException {
        ResponseEntity<String> respuesta = null;
        turnoService.buscarTurnoPorPaciente(idPaciente);
        respuesta = ResponseEntity.status(HttpStatus.OK).body("Turnos para el paciente");
        return respuesta;
    }

    @GetMapping("/listarTurnos")
    public ResponseEntity<Set<TurnoDTO>> listarTurnos() throws ResourceNotFoundException{
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @GetMapping("/buscarPorFecha")
    public ResponseEntity<String> buscarTurnoPorFecha(@PathVariable LocalDateTime fecha) throws ResourceNotFoundException {
        ResponseEntity<String> respuesta;
        turnoService.buscarTurnoPorFecha(fecha);
        respuesta = ResponseEntity.status(HttpStatus.OK).body("Turno encontrado");
        return respuesta;
    }


}
