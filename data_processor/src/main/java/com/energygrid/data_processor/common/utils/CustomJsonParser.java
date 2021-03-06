package com.energygrid.data_processor.common.utils;

import com.energygrid.data_processor.common.events.RegionalEvent;

import com.energygrid.data_processor.common.models.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomJsonParser {

    public RegionalEvent parseToRegionalEvent(String message) throws JsonProcessingException {
        // ATTENTION
        // This class is not the same as the CustomJSONParser found in the status service module
        // This class parses a JSON string that has been created by the simulator, whereas
        // the status service parses a JSON string created by gson in Java.
        // The 2 differences are:
        //      the naming of the fields according to python convention instead of java convention
        //      The parsing of the localDateTime
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(message);
        JsonNode consumptionNode = rootNode.get("consumption");
        // Load consumption data
        JsonNode householdsConsumerGroupNode = consumptionNode.get("households");
        JsonNode householdsConsumersNode = householdsConsumerGroupNode.get("consumers");

        JsonNode bigConsumersGroupNode = consumptionNode.get("big_consumers");
        JsonNode bigConsumersNode = bigConsumersGroupNode.get("consumers");

        JsonNode industriesGroupNode = consumptionNode.get("industries");
        JsonNode industriesConsumersNode = industriesGroupNode.get("consumers");
        // Load production data
        JsonNode productionNode = rootNode.get("production");

        JsonNode windFarmsGroupNode = productionNode.get("wind_farms");
        JsonNode windFarmsProducersNode = windFarmsGroupNode.get("producers");

        JsonNode solarFarmsGroupNode = productionNode.get("solar_farms");
        JsonNode solarFarmsProducersNode = solarFarmsGroupNode.get("producers");

        JsonNode powerPlantsGroupNode = productionNode.get("power_plants");
        JsonNode powerPlantsProducersNode = powerPlantsGroupNode.get("producers");

        JsonNode householdsProducerGroupNode = productionNode.get("households");
        JsonNode householdsProducersNode = householdsProducerGroupNode.get("producers");

        // Create event and all subclasses except for arrays
        RegionalEvent event = new RegionalEvent();
        Consumption consumption = new Consumption();
        ConsumerGroup householdsConsumersGroup = new ConsumerGroup();
        ConsumerGroup bigConsumersGroup = new ConsumerGroup();
        ConsumerGroup industriesGroup = new ConsumerGroup();
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
        householdsConsumersGroup.setTotalConsumers(householdsConsumerGroupNode.get("num_consumers").asInt());
        householdsConsumersGroup.setTotalConsumption(householdsConsumerGroupNode.get("total_consumption").asDouble());
        householdsConsumersGroup.setConsumers(householdConsumers);
        bigConsumersGroup.setTotalConsumers(bigConsumersGroupNode.get("num_consumers").asInt());
        bigConsumersGroup.setTotalConsumption(bigConsumersGroupNode.get("total_consumption").asDouble());
        bigConsumersGroup.setConsumers(bigConsumers);
        industriesGroup.setTotalConsumers(industriesGroupNode.get("num_consumers").asInt());
        industriesGroup.setTotalConsumption(industriesGroupNode.get("total_consumption").asDouble());
        industriesGroup.setConsumers(industries);
        windFarmsGroup.setTotalProducers(windFarmsGroupNode.get("num_producers").asInt());
        windFarmsGroup.setTotalProduction(windFarmsGroupNode.get("total_production").asDouble());
        windFarmsGroup.setProducers(windFarms);
        solarFarmsGroup.setTotalProducers(solarFarmsGroupNode.get("num_producers").asInt());
        solarFarmsGroup.setTotalProduction(solarFarmsGroupNode.get("total_production").asDouble());
        solarFarmsGroup.setProducers(solarFarms);
        powerPlantsGroup.setTotalProducers(powerPlantsGroupNode.get("num_producers").asInt());
        powerPlantsGroup.setTotalProduction(powerPlantsGroupNode.get("total_production").asDouble());
        powerPlantsGroup.setProducers(powerPlants);
        householdsProducersGroup.setTotalProducers(householdsProducerGroupNode.get("num_producers").asInt());
        householdsProducersGroup.setTotalProduction(householdsProducerGroupNode.get("total_production").asDouble());
        householdsProducersGroup.setProducers(householdProducers);

        // Setting consumption and production
        consumption.setHouseholds(householdsConsumersGroup);
        consumption.setBigConsumers(bigConsumersGroup);
        consumption.setIndustries(industriesGroup);
        production.setWindFarms(windFarmsGroup);
        production.setSolarFarms(solarFarmsGroup);
        production.setPowerPlants(powerPlantsGroup);
        production.setHouseholds(householdsProducersGroup);
        // Setting the event
        String dateTime = rootNode.get("date").asText();
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_OFFSET_DATE_TIME).toFormatter();
        event.setLocalDateTime(LocalDateTime.parse(dateTime, dateTimeFormatter));
        event.setRegion(rootNode.get("region").asText());
        event.setConsumption(consumption);
        event.setProduction(production);

        return event;
    }
}
