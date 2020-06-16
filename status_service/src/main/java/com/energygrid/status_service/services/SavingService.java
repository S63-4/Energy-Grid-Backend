package com.energygrid.status_service.services;

import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.common.utils.CustomJsonParser;
import com.energygrid.status_service.repositories.RegionalEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class SavingService {

    private CustomJsonParser jsonParser;
    private RegionalEventRepository regionalEventRepository;

    public SavingService(CustomJsonParser jsonParser, RegionalEventRepository regionalEventRepository) {
        this.jsonParser = jsonParser;
        this.regionalEventRepository = regionalEventRepository;
    }

    public void handleHourEvent(String message) {
        RegionalEvent event = parseToRegionalEvent(message);
        assert event != null;
        event.setPeriod("hour");
        regionalEventRepository.save(event);
        System.out.println(event);
    }

    private RegionalEvent parseToRegionalEvent(String message) {
        try {
            return jsonParser.parseToRegionalEvent(message);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void writeToFile(String message)
            throws IOException, JsonProcessingException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("event.json"));
        writer.write(message);
        writer.close();
        System.out.println(String.format("written file: event.json"));
    }
}
