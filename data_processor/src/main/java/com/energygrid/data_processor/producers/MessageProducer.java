package com.energygrid.data_processor.producers;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

public class MessageProducer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    @Qualifier("data-processor-exchange")
    private DirectExchange directExchange;

    public MessageProducer() {
    }

    public void send(String message) {
        template.convertAndSend(directExchange.getName(), message);
    }
}
