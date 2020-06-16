package com.energygrid.status_service.services;

import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.repositories.RegionalEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public RegionalEvent getRegionalEventByDatetime(LocalDateTime localDateTime) {
        RegionalEvent event = new RegionalEvent();
        return regionalEventRepository.findByLocalDateTime(localDateTime);
    }

    public List<RegionalEvent> getRegionalEventByDates(LocalDateTime startDate, LocalDateTime endDate){
        return null;
    }
}
