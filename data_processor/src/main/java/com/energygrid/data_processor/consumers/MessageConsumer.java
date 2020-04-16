package com.energygrid.data_processor.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MessageConsumer {
    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void receiveRegional(String message) throws InterruptedException {
        System.out.println(String.format("Received message from receiver: %s", message));
    }

    @RabbitListener
    public void receiveNational(String message) throws InterruptedException {
        System.out.println(String.format("Received message from receiver: %s", message));
    }

    @RabbitListener
    public void receiveMarket(String message) throws InterruptedException {
        System.out.println(String.format("Received message from receiver: %s", message));
    }
}
