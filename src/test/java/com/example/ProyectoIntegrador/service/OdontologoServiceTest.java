package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.exceptions.ResourceNotFoundException;
import com.example.ProyectoIntegrador.model.OdontologoDTO;
import com.example.ProyectoIntegrador.persistence.repository.OdontologoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class OdontologoServiceTest {

        @Autowired
        private OdontologoService serviceOdontologoDto;

        @Autowired
        private OdontologoRepository repository;

        private OdontologoDTO odontologoDTO;

        public void testOdontologo(){

            odontologoDTO.setNombre("Carlos");
            odontologoDTO.setApellido("Lopez");
            odontologoDTO.setMatricula("YTR342");
            serviceOdontologoDto.crearOdontologo(odontologoDTO);

            serviceOdontologoDto.crearOdontologo(odontologoDTO);
            Set<OdontologoDTO> odontologos = serviceOdontologoDto.listarOdontologos();

            boolean resultado = odontologos.size() > 0;

            assertTrue(resultado);
        }


}
