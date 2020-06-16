package com.energygrid.status_service.services;

import com.energygrid.status_service.common.dto.StatusDTO;
import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.repositories.RegionalEventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatusService {

    private RegionalEventRepository regionalEventRepository;
    private final ModelMapper modelMapper;

    public StatusService(RegionalEventRepository regionalEventRepository, ModelMapper modelMapper) {
        this.regionalEventRepository = regionalEventRepository;
        this.modelMapper = modelMapper;
    }

    public List<RegionalEvent> getAll() {
        return (List<RegionalEvent>) regionalEventRepository.findAll();
    }

    public RegionalEvent getRegionalEventByDatetime(LocalDateTime localDateTime) {
        RegionalEvent event = new RegionalEvent();
        return regionalEventRepository.findByLocalDateTime(localDateTime);
    }

    public List<StatusDTO> getRegionalEventByDates(LocalDateTime startDate, LocalDateTime endDate){

        List<RegionalEvent> regionalEvents = regionalEventRepository.findByLocalDateTimeBetween(startDate, endDate);
        List<StatusDTO> statusDTOS = new ArrayList<>();

        for (RegionalEvent event: regionalEvents){
            statusDTOS.add(modelMapper.map(event, StatusDTO.class));
        }

        return statusDTOS;
    }
}
