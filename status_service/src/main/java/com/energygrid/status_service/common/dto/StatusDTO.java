package com.energygrid.status_service.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class StatusDTO extends AbstractEventDTO {

    @JsonProperty
    private ConsumptionDTO consumption;
    @JsonProperty
    private ProductionDTO production;

    public StatusDTO() {
    }

    public StatusDTO(LocalDateTime dateTime, String region) {
        super(dateTime, region);
    }

    public ConsumptionDTO getConsumption() {
        return consumption;
    }

    public void setConsumption(ConsumptionDTO consumption) {
        this.consumption = consumption;
    }

    public ProductionDTO getProduction() {
        return production;
    }

    public void setProduction(ProductionDTO production) {
        this.production = production;
    }
}
