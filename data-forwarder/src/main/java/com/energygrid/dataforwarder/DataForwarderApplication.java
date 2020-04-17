package com.energygrid.dataforwarder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataForwarderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataForwarderApplication.class, args);
    }
}
