package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.exceptions.BadRequestException;
import com.example.ProyectoIntegrador.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegrador.model.OdontologoDTO;
import com.example.ProyectoIntegrador.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private IOdontologoService odontologoService;

    @PostMapping("/crearOdontologo")
    public ResponseEntity<String> crearOdontologo(@RequestBody OdontologoDTO odontologoDTO) throws HttpServerErrorException.InternalServerError {
        ResponseEntity<String> respuesta;
        odontologoService.crearOdontologo(odontologoDTO);
        respuesta = ResponseEntity.status(HttpStatus.CREATED).body("Odontologo Creado");
        return respuesta;
    }

    @GetMapping("/listarOdontologos")
    public ResponseEntity<Set<OdontologoDTO>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @GetMapping("/buscarOdontologo/{id}")
    public ResponseEntity<OdontologoDTO> buscarOdontologoPorID(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(odontologoService.buscarOdontologoPorID(id));
    }

    @PutMapping("/modificarOdontologo")
    public ResponseEntity<String> modificarOdontologo(@RequestBody OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        ResponseEntity<String> respuesta;
        if(odontologoDTO.getId() != null){
            odontologoService.modificarOdontologo(odontologoDTO);
            respuesta = ResponseEntity.status(HttpStatus.OK).body("Odontologo modificado");
        }else{
            throw new ResourceNotFoundException("Odontologo inválido");
        }

        return respuesta;
    }

    @DeleteMapping("/eliminarOdontologo/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<String> respuesta;
        odontologoService.eliminarOdontologo(id);
        respuesta = ResponseEntity.status(HttpStatus.OK).body("Odontologo Eliminado");
        return respuesta;
    }

}
