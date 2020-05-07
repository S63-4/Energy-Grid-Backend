package com.energygrid.data_processor.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseholdProducer extends Producer {

    @JsonProperty
    private int numConnections;

    public HouseholdProducer(String name, double production, int numConnections) {
        super(name, production);
        this.numConnections = numConnections;
    }

    public HouseholdProducer() {
    }

    public int getNumConnections() {
        return numConnections;
    }

    public void setNumConnections(int numConnections) {
        this.numConnections = numConnections;
    }
}
