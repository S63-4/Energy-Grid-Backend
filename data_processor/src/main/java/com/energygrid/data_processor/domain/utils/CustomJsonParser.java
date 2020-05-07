package com.energygrid.data_processor.domain.utils;

import com.energygrid.data_processor.domain.events.RegionalEvent;

import com.energygrid.data_processor.domain.models.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ReferenceType;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Component
public class CustomJsonParser {

    public RegionalEvent parseToRegionalEvent(String message) throws JsonProcessingException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(message);
        JsonNode consumptionNode = rootNode.get("consumption");
        JsonNode householdsConsumerGroupNode = consumptionNode.get("households");
        JsonNode householdsConsumersNode = householdsConsumerGroupNode.get("consumers");

        JsonNode productionNode = rootNode.get("production");

        RegionalEvent event = new RegionalEvent();
        Consumption consumption = new Consumption();
        ConsumerGroup householdsConsumers = new ConsumerGroup();
        ConsumerGroup bigConsumers = new ConsumerGroup();
        ConsumerGroup industries = new ConsumerGroup();
        Production production = new Production();
        ProducerGroup windFarms = new ProducerGroup();
        ProducerGroup solarFarms = new ProducerGroup();
        ProducerGroup powerPlants = new ProducerGroup();
        ProducerGroup householdsProducers = new ProducerGroup();

        ArrayList<HouseholdConsumer> householdConsumers = mapper.readValue(householdsConsumersNode.toString(), new TypeReference<ArrayList<HouseholdConsumer>>() {});
        householdsConsumers.setConsumers(householdConsumers);

        event.setConsumption(consumption);
        event.setProduction(production);
        return null;
    }
}
