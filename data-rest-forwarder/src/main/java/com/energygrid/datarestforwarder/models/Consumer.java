package com.energygrid.datarestforwarder.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Consumer {

    @JsonProperty
    private String name;
    @JsonProperty
    private double consumption;

    public Consumer() {

    }

    public Consumer(String name, double consumption) {
        this.name = name;
        this.consumption = consumption;
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
