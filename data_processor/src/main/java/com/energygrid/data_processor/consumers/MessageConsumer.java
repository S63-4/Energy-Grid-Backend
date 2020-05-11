package com.energygrid.data_processor.consumers;


import com.energygrid.data_processor.domain.events.RegionalEvent;
import com.energygrid.data_processor.services.RegionalService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class MessageConsumer {

    @Autowired
    private RegionalService regionalService;

    @RabbitListener(queues = "#{regionalSimQueue.name}")
    public void receiveRegional(String message) throws InterruptedException {
        regionalService.handleEvent(message);
        // DEBUG code for writing JSON to file
//        try {
//            writeToFile(message);
//        } catch (JsonProcessingException e) {
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }

    @RabbitListener(queues = "#{nationalSimQueue.name}")
    public void receiveNational(String message) throws InterruptedException {

    }

    @RabbitListener(queues = "#{marketSimQueue.name}")
    public void receiveMarket(String message) throws InterruptedException {
        System.out.println(message);
    }

    public String getDate(String json) throws JsonProcessingException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(json);
        Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
        String date = "null";
        while (fieldsIterator.hasNext()) {
            Map.Entry<String,JsonNode> field = fieldsIterator.next();
            if (field.getKey().equals("date")) {
                date = field.getValue().toString();
            }
        }
        date = date.replace(":", "-").substring(1,20);
        return date;
    }

    private void writeToFile(String message)
            throws IOException, JsonProcessingException {
        String filename = getDate(message);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".json"));
        writer.write(message);
        writer.close();
        System.out.println(String.format("written file: %s.json", filename ));
    }
}
