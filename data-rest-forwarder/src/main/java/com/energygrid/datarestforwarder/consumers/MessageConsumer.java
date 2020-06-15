package com.energygrid.datarestforwarder.consumers;

import com.energygrid.datarestforwarder.RESTful.MessageController;
import com.energygrid.datarestforwarder.events.RegionalEvent;
import com.energygrid.datarestforwarder.models.SharedJSON;
import com.energygrid.datarestforwarder.models.utils.CustomJsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

public class MessageConsumer {
    @Autowired
    private MessageController controller;

    private Gson gson = new Gson();
    CustomJsonParser parser = new CustomJsonParser();
    SharedJSON sharedJSON = new SharedJSON();

    public MessageConsumer() {
    }

    @RabbitListener(queues = "#{regionalSimQueue.name}")
    public void receiveRegional(String message) throws IOException {
        File jsonFile = new File(
                getClass().getClassLoader().getResource("regional.json").getFile()
        );
        RegionalEvent event = parser.parseToRegionalEvent(message);
        double totalConsumption = event.getConsumption().getIndustries().getTotalConsumption() + event.getConsumption().getBigConsumers().getTotalConsumption() + event.getConsumption().getHouseholds().getTotalConsumption();
        double totalProduction = event.getProduction().getHouseholds().getTotalProduction() + event.getProduction().getPowerPlants().getTotalProduction() + event.getProduction().getSolarFarms().getTotalProduction() + event.getProduction().getWindFarms().getTotalProduction();
        sharedJSON.setConsumption(totalConsumption);
        sharedJSON.setProduction(totalProduction);
        sharedJSON.setDate(event.getLocalDateTime());
        writeToFile(gson.toJson(sharedJSON), jsonFile);
    }

    private void writeToFile(String message, File jsonFile)
            throws IOException, JsonProcessingException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
        writer.write(message);
        writer.close();
    }
}