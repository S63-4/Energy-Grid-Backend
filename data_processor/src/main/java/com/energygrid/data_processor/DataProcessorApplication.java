package com.energygrid.data_processor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class DataProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataProcessorApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
//            String json = "{\"date\" : \"2020-05-04T11:20:21\", \"region\" : \"zeeland\", \"consumption\" : {\"households\" : { \"num_consumers\" : 1, \"total_consumption\" : 10, \"consumers\" : [{\"name\" : \"zipcode\", \"num_connections\": 5, \"consumption\" : 10}]}, \"big_consumers\" : { \"num_consumers\" : 0, \"total_consumption\" : 0, \"consumers\" : []}, \"industries\" : { \"num_consumers\" : 0, \"total_consumption\" : 0, \"consumers\" : []}}, \"production\" : {\"wind_farms\" : { \"num_producers\" : 0, \"total_production\" : 0, \"producers\" : []}, \"solar_farms\" : { \"num_producers\" : 0, \"total_production\" : 0, \"producers\" : []}, \"power_plants\" : { \"num_producers\" : 0, \"total_production\" : 0, \"producers\" : []}, \"households\" : { \"num_producers\" : 0, \"total_production\" : 0, \"producers\" : []}}}";
//            CustomJsonParser jsonParser = new CustomJsonParser();
//            RegionalEvent event = jsonParser.parseToRegionalEvent(json);
//            System.out.println(event);
        };
    }

}
