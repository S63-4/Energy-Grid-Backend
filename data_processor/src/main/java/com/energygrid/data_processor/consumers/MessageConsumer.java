package com.energygrid.data_processor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MessageConsumer {
    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void receive(String message) throws InterruptedException {
        System.out.println(String.format("Received message from receiver: %s", message));
    }
}
