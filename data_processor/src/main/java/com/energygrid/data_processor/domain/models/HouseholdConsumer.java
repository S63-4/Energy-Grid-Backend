package com.energygrid.data_processor.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseholdConsumer extends Consumer {

    @JsonProperty("num_connection")
    private int numConnections;

    public HouseholdConsumer(String name, double consumption, int numConnections) {
        super(name, consumption);
        this.numConnections = numConnections;
    }

    public HouseholdConsumer() {
    }

    public int getNumConnections() {
        return numConnections;
    }

    public void setNumConnections(int numConnections) {
        this.numConnections = numConnections;
    }
}
