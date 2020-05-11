package com.energygrid.dataforwarder.events.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Consumption {

    @JsonProperty("households")
    private ConsumerGroup households;
    @JsonProperty("big_consumers")
    private ConsumerGroup bigConsumers;
    @JsonProperty("industries")
    private  ConsumerGroup industries;

    public Consumption(ConsumerGroup households, ConsumerGroup bigConsumers, ConsumerGroup industries) {
        this.households = households;
        this.bigConsumers = bigConsumers;
        this.industries = industries;
    }

    public Consumption() {

    }

    public ConsumerGroup getHouseholds() {
        return households;
    }

    public void setHouseholds(ConsumerGroup households) {
        this.households = households;
    }

    public ConsumerGroup getBigConsumers() {
        return bigConsumers;
    }

    public void setBigConsumers(ConsumerGroup bigConsumers) {
        this.bigConsumers = bigConsumers;
    }

    public ConsumerGroup getIndustries() {
        return industries;
    }

    public void setIndustries(ConsumerGroup industries) {
        this.industries = industries;
    }
}
