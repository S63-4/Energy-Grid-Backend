package com.energygrid.status_service.common.utils;

import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.common.models.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class CustomJsonParser {

    public RegionalEvent parseToRegionalEvent(String message) throws JsonProcessingException {
        // ATTENTION
        // This class is not the same as the CustomJSONParser found in the data processor module
        // This class parses a JSON string that has been created with gson in Java, whereas
        // the parser in the data processor parses json from the simulator.
        // The 2 differences are:
        //      the naming of the fields according to java convention instead of python convention
        //      The parsing of the localDateTime
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(message);
        JsonNode consumptionNode = rootNode.get("consumption");
        // Load consumption data
        JsonNode householdsConsumerGroupNode = consumptionNode.get("households");
        JsonNode householdsConsumersNode = householdsConsumerGroupNode.get("consumers");

        JsonNode bigConsumersGroupNode = consumptionNode.get("bigConsumers");
        JsonNode bigConsumersNode = bigConsumersGroupNode.get("consumers");

        JsonNode industriesGroupNode = consumptionNode.get("industries");
        JsonNode industriesConsumersNode = industriesGroupNode.get("consumers");
        // Load production data
        JsonNode productionNode = rootNode.get("production");

        JsonNode windFarmsGroupNode = productionNode.get("windFarms");
        JsonNode windFarmsProducersNode = windFarmsGroupNode.get("producers");

        JsonNode solarFarmsGroupNode = productionNode.get("solarFarms");
        JsonNode solarFarmsProducersNode = solarFarmsGroupNode.get("producers");

        JsonNode powerPlantsGroupNode = productionNode.get("powerPlants");
        JsonNode powerPlantsProducersNode = powerPlantsGroupNode.get("producers");

        JsonNode householdsProducerGroupNode = productionNode.get("households");
        JsonNode householdsProducersNode = householdsProducerGroupNode.get("producers");

        // Create event and all subclasses except for arrays
        RegionalEvent event = new RegionalEvent();
        Consumption consumption = new Consumption();
        ConsumerGroup householdsConsumersGroup = new ConsumerGroup();
        ConsumerGroup bigConsumersGroup = new ConsumerGroup();
        ConsumerGroup industriesConsumersGroup = new ConsumerGroup();
        Production production = new Production();
        ProducerGroup windFarmsGroup = new ProducerGroup();
        ProducerGroup solarFarmsGroup = new ProducerGroup();
        ProducerGroup powerPlantsGroup = new ProducerGroup();
        ProducerGroup householdsProducersGroup = new ProducerGroup();

        // Create arrays
        List<HouseholdConsumer> householdConsumers = mapper.readValue(householdsConsumersNode.toString(), new TypeReference<List<HouseholdConsumer>>() {});
        List<Consumer> bigConsumers = mapper.readValue(bigConsumersNode.toString(), new TypeReference<List<Consumer>>() {});
        List<Consumer> industries = mapper.readValue(industriesConsumersNode.toString(), new TypeReference<List<Consumer>>() {});
        List<Producer> windFarms = mapper.readValue(windFarmsProducersNode.toString(), new TypeReference<List<Producer>>() {});
        List<Producer> solarFarms = mapper.readValue(solarFarmsProducersNode.toString(), new TypeReference<List<Producer>>() {});
        List<Producer> powerPlants = mapper.readValue(powerPlantsProducersNode.toString(), new  TypeReference<List<Producer>>() {});
        List<HouseholdProducer> householdProducers = mapper.readValue(householdsProducersNode.toString(), new TypeReference<List<HouseholdProducer>>() {});

        // Setting groups
        householdsConsumersGroup.setTotalConsumers(householdsConsumerGroupNode.get("totalConsumers").asInt());
        householdsConsumersGroup.setTotalConsumption(householdsConsumerGroupNode.get("totalConsumption").asDouble());
        householdsConsumersGroup.setConsumers(householdConsumers);
        householdsConsumersGroup.setType("households");
        bigConsumersGroup.setTotalConsumers(bigConsumersGroupNode.get("totalConsumers").asInt());
        bigConsumersGroup.setTotalConsumption(bigConsumersGroupNode.get("totalConsumption").asDouble());
        bigConsumersGroup.setConsumers(bigConsumers);
        bigConsumersGroup.setType("big-consumers");
        industriesConsumersGroup.setTotalConsumers(industriesGroupNode.get("totalConsumers").asInt());
        industriesConsumersGroup.setTotalConsumption(industriesGroupNode.get("totalConsumption").asDouble());
        industriesConsumersGroup.setConsumers(industries);
        industriesConsumersGroup.setType("industries");
        windFarmsGroup.setTotalProducers(windFarmsGroupNode.get("totalProducers").asInt());
        windFarmsGroup.setTotalProduction(windFarmsGroupNode.get("totalProduction").asDouble());
        windFarmsGroup.setProducers(windFarms);
        solarFarmsGroup.setTotalProducers(solarFarmsGroupNode.get("totalProducers").asInt());
        solarFarmsGroup.setTotalProduction(solarFarmsGroupNode.get("totalProduction").asDouble());
        solarFarmsGroup.setProducers(solarFarms);
        powerPlantsGroup.setTotalProducers(powerPlantsGroupNode.get("totalProducers").asInt());
        powerPlantsGroup.setTotalProduction(powerPlantsGroupNode.get("totalProduction").asDouble());
        powerPlantsGroup.setProducers(powerPlants);
        householdsProducersGroup.setTotalProducers(householdsProducerGroupNode.get("totalProducers").asInt());
        householdsProducersGroup.setTotalProduction(householdsProducerGroupNode.get("totalProduction").asDouble());
        householdsProducersGroup.setProducers(householdProducers);

        // Setting consumption and production
        consumption.setGroups(Arrays.asList(householdsConsumersGroup, bigConsumersGroup, industriesConsumersGroup));
        production.setGroups(Arrays.asList(windFarmsGroup, solarFarmsGroup, powerPlantsGroup, householdsProducersGroup));
        // Setting the event
        JsonNode dateTimeNode = rootNode.get("localDateTime");
        LocalDateTime dateTime = parseDateTime(dateTimeNode);
        event.setLocalDateTime(dateTime);
        event.setRegion(rootNode.get("region").asText());
        event.setConsumption(consumption);
        event.setProduction(production);

        return event;
    }

    private LocalDateTime parseDateTime(JsonNode dateTimeNode) {
        int year = dateTimeNode.get("date").get("year").asInt();
        int month = dateTimeNode.get("date").get("month").asInt();
        int day = dateTimeNode.get("date").get("day").asInt();
        int hour = dateTimeNode.get("time").get("hour").asInt();
        int minute = dateTimeNode.get("time").get("minute").asInt();
        int second = dateTimeNode.get("time").get("second").asInt();
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
