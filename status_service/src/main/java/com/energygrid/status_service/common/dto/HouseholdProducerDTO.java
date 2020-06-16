package com.energygrid.status_service.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseholdProducerDTO extends ProducerDTO {

    @JsonProperty("num_connections")
    private int numConnections;

    public HouseholdProducerDTO() {
    }

    public HouseholdProducerDTO(String name, double production, int numConnections) {
        super(name, production);
        this.numConnections = numConnections;
    }

    public int getNumConnections() {
        return numConnections;
    }

    public void setNumConnections(int numConnections) {
        this.numConnections = numConnections;
    }
}
