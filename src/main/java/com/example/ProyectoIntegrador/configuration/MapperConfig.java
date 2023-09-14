package com.example.ProyectoIntegrador.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    private static Logger logger = LogManager.getLogger(MapperConfig.class);

    public MapperConfig(){
        logger.info("Instanciando el MapperConfig");
    }

    @Bean
    public ModelMapper getModelMapper(){
        logger.info("Instanciando el ModelMapper");
        return new ModelMapper();
    }
}
