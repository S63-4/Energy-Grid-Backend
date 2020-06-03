package com.energygrid.datarestforwarder.consumers;

import com.energygrid.datarestforwarder.RESTful.MessageController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

public class MessageConsumer {
    @Autowired
    private MessageController controller;

    private Gson gson = new Gson();

    public MessageConsumer() {
    }

    @RabbitListener(queues = "#{regionalSimQueue.name}")
    public void receiveRegional(String message) throws IOException {
        File jsonFile = new File("/Users/lottediesveld/Documents/Fontys/IntelliJ IDEA/Semester 6/Energy-Grid-Backend/data-rest-forwarder/src/main/java/com/energygrid/datarestforwarder/RESTful/JSONfiles/regional.json");
        writeToFile(message, jsonFile);
    }

    @RabbitListener(queues = "#{nationalSimQueue.name}")
    public void receiveNational(String message) throws IOException {
        File jsonFile = new File("/Users/lottediesveld/Documents/Fontys/IntelliJ IDEA/Semester 6/Energy-Grid-Backend/data-rest-forwarder/src/main/java/com/energygrid/datarestforwarder/RESTful/JSONfiles/national.json");
        writeToFile(message, jsonFile);
    }

    @RabbitListener(queues = "#{marketSimQueue.name}")
    public void receiveMarket(String message) throws IOException {
        File jsonFile = new File("/Users/lottediesveld/Documents/Fontys/IntelliJ IDEA/Semester 6/Energy-Grid-Backend/data-rest-forwarder/src/main/java/com/energygrid/datarestforwarder/RESTful/JSONfiles/market.json");
        writeToFile(message, jsonFile);
    }

    private void writeToFile(String message, File jsonFile)
            throws IOException, JsonProcessingException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
        writer.write(message);
        writer.close();
    }
}