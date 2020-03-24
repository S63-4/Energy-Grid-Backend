package com.energygrid.data_processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataProcessorApplication.class, args);
    }
}
