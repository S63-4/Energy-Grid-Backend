package com.energygrid.data_processor.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseholdConsumer extends Consumer {

    @JsonProperty("num_connections")
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
