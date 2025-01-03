package com.EcoDelis.infraestructura;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfiguracionDeApp {

    @Bean
    public RestTemplate plantillaDeRest() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper crearObjectMapper() {
        return new ObjectMapper();
    }
}