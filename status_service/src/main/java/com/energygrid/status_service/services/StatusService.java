package com.energygrid.status_service.services;

import com.energygrid.status_service.common.dto.CustomerStatusDTO;
import com.energygrid.status_service.common.dto.StatusDTO;
import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.common.models.Consumer;
import com.energygrid.status_service.common.models.ConsumerWithDateTime;
import com.energygrid.status_service.common.models.Producer;
import com.energygrid.status_service.repositories.RegionalEventRepository;
import org.checkerframework.checker.units.qual.C;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public List<CustomerStatusDTO> getCustomerStatus(String zipcode, LocalDateTime startDate, LocalDateTime endDate) {
        List<ConsumerWithDateTime> consumers = regionalEventRepository.findConsumersByZipcodeBetween(zipcode, startDate, endDate);
//        List<Producer> producer = regionalEventRepository.findProducerByZipcodeBetween(zipcode, startDate, endDate);
        List<CustomerStatusDTO> statusDTOS = new ArrayList<>();
        for (ConsumerWithDateTime consumerWithDateTime: consumers) {
            Consumer consumer = new Consumer(consumerWithDateTime.getName(), consumerWithDateTime.getConsumption());
            Producer producer = new Producer();
            LocalDateTime localDateTime = consumerWithDateTime.getLocalDateTime();
            String iso = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime);
            statusDTOS.add(new CustomerStatusDTO(iso, consumer.getName(), consumer.getConsumption(), producer.getProduction()));
        }
        return statusDTOS;
    }
}
