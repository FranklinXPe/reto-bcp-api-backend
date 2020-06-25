package com.bcp.tipocambio.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TipoCambioConfig {

    //Crear una utileria que nos servira para hacer el mapeo entre el objeto
    // de entidad (Entity) y el objeto de transferencia (DTO)
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
