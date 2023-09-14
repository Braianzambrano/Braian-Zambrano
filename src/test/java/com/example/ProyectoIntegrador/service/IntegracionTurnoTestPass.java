package com.example.ProyectoIntegrador.service;


import com.example.ProyectoIntegrador.persistence.entities.Odontologo;
import com.example.ProyectoIntegrador.persistence.entities.Paciente;
import com.example.ProyectoIntegrador.persistence.entities.Turno;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.Jsons;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegracionTurnoTestPass {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registrarTurno() throws Exception{
        Turno turno = new Turno(LocalDateTime.of(2023,06,29,03,06),new Paciente("Carlos","Lopez","15487851", LocalDate.of(2020,03,18)),new Odontologo("Jorge", "Perez","MN1234"));
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Jsons.asJsonString(turno)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertTrue(response.getResponse().getContentAsString().length() > 0);

    }
}
