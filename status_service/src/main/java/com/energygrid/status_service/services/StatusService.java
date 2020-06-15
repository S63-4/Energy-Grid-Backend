package com.energygrid.status_service.services;

import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.repositories.RegionalEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    private RegionalEventRepository regionalEventRepository;

    public StatusService(RegionalEventRepository regionalEventRepository) {
        this.regionalEventRepository = regionalEventRepository;
    }

    public List<RegionalEvent> getAll() {

        return (List<RegionalEvent>) regionalEventRepository.findAll();
    }

    public List<RegionalEvent> getRegionalEventByZipCode(String zipcode) {
        return null;
    }
}
