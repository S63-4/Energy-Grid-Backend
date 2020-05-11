package com.energygrid.status_service.services;

import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.common.utils.CustomJsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class SavingService {

    private CustomJsonParser jsonParser;

    public SavingService(CustomJsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public void handleEvent(String message) {
        RegionalEvent event = parseToRegionalEvent(message);
        System.out.println();
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
