package com.example.ProyectoIntegrador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> procesarResourceNotFoundException(ResourceNotFoundException ex){
        Map<String,String> mensajeExcepcion = new HashMap<>(); //{error : mensaje}
        mensajeExcepcion.put("error","Recurso no encontrado: " + ex.getMessage());
        return mensajeExcepcion;
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarBadRequestException(BadRequestException ex){
        Map<String,String> mensajeExcepcion = new HashMap<>();
        mensajeExcepcion.put("error","Solicitud Inválida: " + ex.getMessage());
        return mensajeExcepcion;
    }



}
