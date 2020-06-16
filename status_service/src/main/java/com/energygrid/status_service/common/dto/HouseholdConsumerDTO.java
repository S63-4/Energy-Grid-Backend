package com.energygrid.status_service.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseholdConsumerDTO extends ConsumerDTO {

    @JsonProperty("num_connections")
    private int numConnections;

    public HouseholdConsumerDTO() {
    }

    public HouseholdConsumerDTO(String name, double consumption, int numConnections) {
        super(name, consumption);
        this.numConnections = numConnections;
    }

    public int getNumConnections() {
        return numConnections;
    }

    public void setNumConnections(int numConnections) {
        this.numConnections = numConnections;
    }
}
