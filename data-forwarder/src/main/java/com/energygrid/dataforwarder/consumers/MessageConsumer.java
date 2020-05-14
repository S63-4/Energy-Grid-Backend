package com.energygrid.dataforwarder.consumers;

import com.energygrid.dataforwarder.websocket.MessageController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageConsumer {
    @Autowired
    private MessageController controller;

    public MessageConsumer() {
    }

    @RabbitListener(queues = "#{regionalSimQueue.name}")
    public void receiveRegional(String message) throws Exception {
        controller.regional(message);
    }

    @RabbitListener(queues = "#{nationalSimQueue.name}")
    public void receiveNational(String message) throws InterruptedException {
        controller.national(message);
    }

    @RabbitListener(queues = "#{marketSimQueue.name}")
    public void receiveMarket(String message) throws InterruptedException {
        controller.market(message);
    }
}