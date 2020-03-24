package com.energygrid.data_processor;

import com.energygrid.data_processor.test.MyStompSessionHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

@SpringBootApplication
@EnableEurekaClient
public class DataProcessorApplication {

    private static String URL = "ws://localhost:8";

    public static void main(String[] args) {
        SpringApplication.run(DataProcessorApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(){
        return args -> {
            WebSocketClient client = new StandardWebSocketClient();
            WebSocketStompClient stompClient = new WebSocketStompClient(client);

            stompClient.setMessageConverter(new MappingJackson2MessageConverter());

            StompSessionHandler sessionHandler = new MyStompSessionHandler();
            stompClient.connect(URL, sessionHandler);

            new Scanner(System.in).nextLine(); // Don't close immediately.
        };
    }
}
