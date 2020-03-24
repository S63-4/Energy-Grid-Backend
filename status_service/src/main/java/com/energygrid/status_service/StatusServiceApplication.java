package com.energygrid.status_service;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}, scanBasePackages = {"com.energygrid"})
@ComponentScan({"com.EnergyGrid.common","com.EnergyGrid.status_service"})
public class StatusServiceApplication {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(StatusServiceApplication.class, args);
    }


}
