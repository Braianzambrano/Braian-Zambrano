package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.persistence.entities.Odontologo;
import com.example.ProyectoIntegrador.persistence.entities.Paciente;
import com.example.ProyectoIntegrador.persistence.entities.Turno;
import org.junit.Assert;
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
public class IntegracionTurnoTestFail {

    @Autowired
    private MockMvc mockMvc;

    public void registrarTurno() throws Exception{
        Turno turno = new Turno(LocalDateTime.of(2023,06,30,03,07),new Paciente("Sofia","Dominguez","7788992233", LocalDate.of(2020,03,17)),new Odontologo("Juan", "Campos","MN1256"));
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(turno)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());

    }
}
