package com.energygrid.data_processor.services;

import com.energygrid.data_processor.domain.events.RegionalEvent;
import com.energygrid.data_processor.domain.utils.CustomJsonParser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class RegionalService extends AbstractService {

    private CustomJsonParser jsonParser;

    public RegionalService(CustomJsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public void handleEvent(String message) {
        try {
            RegionalEvent event = jsonParser.parseToRegionalEvent(message);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
}
