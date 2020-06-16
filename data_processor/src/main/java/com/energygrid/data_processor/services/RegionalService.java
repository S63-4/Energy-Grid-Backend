package com.energygrid.data_processor.services;

import com.energygrid.data_processor.common.events.RegionalEvent;
import com.energygrid.data_processor.common.utils.CustomJsonParser;
import com.energygrid.data_processor.factories.RegionalBucketFactory;
import com.energygrid.data_processor.factories.buckets.HourBucketRegional;
import com.energygrid.data_processor.producers.MessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegionalService {

    private CustomJsonParser jsonParser;
    private RegionalBucketFactory regionalBucketFactory;
    private MessageProducer messageProducer;

    public RegionalService(CustomJsonParser jsonParser, RegionalBucketFactory regionalBucketFactory, MessageProducer messageProducer) {
        this.jsonParser = jsonParser;
        this.regionalBucketFactory = regionalBucketFactory;
        this.messageProducer = messageProducer;
    }

    public void handleEvent(String message) {
        RegionalEvent event = parseToRegionalEvent(message);
        assert event != null : "event is null";
        saveToHour(event);
//        saveToDay(event);
//        saveToMonth(event);
    }

    private void saveToHour(RegionalEvent event) {
        LocalDateTime dateTime = event.getLocalDateTime();
        HourBucketRegional bucket = regionalBucketFactory.getHourBucket(dateTime);
        // See if bucket still belongs to current hour
        if(bucket.isExpired(dateTime)) {
            // Save expired bucket and ask for new one
            System.out.println("Save expired bucket and ask for new one...");
            Gson gson = new Gson();
            String eventJSON = gson.toJson(bucket.getEvent());
            messageProducer.sendRegionalHourMessage(eventJSON);
            bucket = regionalBucketFactory.newHourBucket(dateTime);
        }
        bucket.add(event);
        // DEBUG CODE
        // Code implemented to skip the hour wait time for the production code to run
//        Gson gson = new Gson();
//        String testJSON = gson.toJson(bucket.getEvent());
//        messageProducer.sendRegionalHourMessage(testJSON);
//        System.out.println();
    }

    private void saveToDay() {

    }

    private void saveToMonth() {

    }

    private RegionalEvent parseToRegionalEvent(String message) {
        try {
            return jsonParser.parseToRegionalEvent(message);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
