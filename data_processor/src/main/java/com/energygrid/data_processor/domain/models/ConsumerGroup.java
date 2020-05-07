package com.energygrid.data_processor.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ConsumerGroup {

    @JsonProperty("num_consumers")
    private int totalConsumers;
    @JsonProperty("total_consumption")
    private double totalConsumption;
    @JsonProperty("consumers")
    private ArrayList<Consumer> consumers;

    public ConsumerGroup(int totalConsumers, double totalConsumption, ArrayList<Consumer> consumers) {
        this.totalConsumers = totalConsumers;
        this.totalConsumption = totalConsumption;
        this.consumers = consumers;
    }

    public ConsumerGroup() {

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

    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }
}
