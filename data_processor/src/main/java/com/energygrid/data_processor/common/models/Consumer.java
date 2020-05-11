package com.energygrid.data_processor.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Consumer {

    @JsonProperty
    private String name;
    @JsonProperty
    private double consumption;

    public Consumer(String name, double consumption) {
        this.name = name;
        this.consumption = consumption;
    }

    public Consumer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }
}
