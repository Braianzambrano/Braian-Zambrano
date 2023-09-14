package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegrador.model.OdontologoDTO;
import com.example.ProyectoIntegrador.model.PacienteDTO;
import com.example.ProyectoIntegrador.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private IPacienteService pacienteService;

    @PostMapping("/crearPaciente")
    public ResponseEntity<String> crearPaciente(@RequestBody PacienteDTO pacienteDTO){
        ResponseEntity<String> respuesta = null;
        pacienteService.crearPaciente(pacienteDTO);
        respuesta = ResponseEntity.status(HttpStatus.OK).body("Paciente creado");
        return respuesta;
    }

    @GetMapping("/listarPacientes")
    public ResponseEntity<Set<PacienteDTO>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/buscarPaciente/{id}")
    public ResponseEntity<PacienteDTO> buscarPacientePorID(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.buscarPacientePorId(id));
    }

    @PutMapping("/modificarPaciente")
    public ResponseEntity<String> modificarPaciente(@RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        ResponseEntity<String> respuesta;
        pacienteService.modificarPaciente(pacienteDTO);
        respuesta = ResponseEntity.status(HttpStatus.OK).body("Datos modificados");
        return respuesta;
    }

    @DeleteMapping("/eliminarPaciente/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<String> respuesta = null;
        pacienteService.eliminarPaciente(id);
        respuesta = ResponseEntity.status(HttpStatus.OK).body("Paciente Eliminado");
        return respuesta;
    }


}
