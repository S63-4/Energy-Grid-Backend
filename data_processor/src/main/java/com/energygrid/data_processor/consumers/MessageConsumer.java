package com.energygrid.data_processor.consumers;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class MessageConsumer {
    @RabbitListener(queues = "#{regionalSimQueue.name}")
    public void receiveRegional(String message) throws InterruptedException {
        try {
            writeToFile(message);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "#{nationalSimQueue.name}")
    public void receiveNational(String message) throws InterruptedException {
        System.out.println(message);
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
        date = date.substring(1,10);
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
