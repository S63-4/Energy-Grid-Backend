package com.energygrid.datarestforwarder.consumers;

import com.energygrid.datarestforwarder.RESTful.MessageController;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileWriter;
import java.io.IOException;

public class MessageConsumer {
    @Autowired
    private MessageController controller;

    private Gson gson = new Gson();

    public MessageConsumer() {
    }

    @RabbitListener(queues = "#{regionalSimQueue.name}")
    public void receiveRegional(String message) throws IOException {
        gson.toJson(message, new FileWriter("com/energygrid/datarestforwarder/RESTful/JSONfiles/regional.json"));
    }

    @RabbitListener(queues = "#{nationalSimQueue.name}")
    public void receiveNational(String message) throws IOException {
        gson.toJson(message, new FileWriter("com/energygrid/datarestforwarder/RESTful/JSONfiles/national.json"));
    }

    @RabbitListener(queues = "#{marketSimQueue.name}")
    public void receiveMarket(String message) throws IOException {
        gson.toJson(message, new FileWriter("com/energygrid/datarestforwarder/RESTful/JSONfiles/market.json"));
    }
}