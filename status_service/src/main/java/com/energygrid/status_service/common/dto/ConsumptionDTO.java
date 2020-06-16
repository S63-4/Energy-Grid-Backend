package com.energygrid.status_service.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsumptionDTO {

    @JsonProperty("households")
    private ConsumerGroupDTO households;
    @JsonProperty("big_consumers")
    private ConsumerGroupDTO bigConsumers;
    @JsonProperty("industries")
    private ConsumerGroupDTO industries;

    public ConsumptionDTO() {

    }

    public ConsumptionDTO(ConsumerGroupDTO households, ConsumerGroupDTO bigConsumers, ConsumerGroupDTO industries) {
        this.households = households;
        this.bigConsumers = bigConsumers;
        this.industries = industries;
    }

    public ConsumerGroupDTO getHouseholds() {
        return households;
    }

    public void setHouseholds(ConsumerGroupDTO households) {
        this.households = households;
    }

    public ConsumerGroupDTO getBigConsumers() {
        return bigConsumers;
    }

    public void setBigConsumers(ConsumerGroupDTO bigConsumers) {
        this.bigConsumers = bigConsumers;
    }

    public ConsumerGroupDTO getIndustries() {
        return industries;
    }

    public void setIndustries(ConsumerGroupDTO industries) {
        this.industries = industries;
    }
}
