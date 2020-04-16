package com.energygrid.data_processor.producers;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class MessageProducer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange directExchange;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        template.convertAndSend(directExchange.getName(), "hello");
        System.out.println("sent message");
    }
}