package com.energygrid.datarestforwarder.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConsumerGroup {

    @JsonProperty("num_consumers")
    private int totalConsumers;
    @JsonProperty("total_consumption")
    private double totalConsumption;
    @JsonProperty("consumers")
    private List<Consumer> consumers;
    
    public ConsumerGroup() {

    }

    public ConsumerGroup(int totalConsumers, double totalConsumption, List<Consumer> consumers) {
        this.totalConsumers = totalConsumers;
        this.totalConsumption = totalConsumption;
        this.consumers = consumers;
    }

    public int getTotalConsumers() {
        return totalConsumers;
    }

    public void setTotalConsumers(int totalConsumers) {
        this.totalConsumers = totalConsumers;
    }

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public <T extends Consumer> void setConsumers(List<T> consumers) {
        this.consumers = (List<Consumer>) consumers;
    }
}
