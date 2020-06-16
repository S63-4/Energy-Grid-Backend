package com.energygrid.status_service.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProducerDTO {

    @JsonProperty
    private String name;
    @JsonProperty
    private double production;

    public ProducerDTO() {
    }

    public ProducerDTO(String name, double production) {
        this.name = name;
        this.production = production;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProduction() {
        return production;
    }

    public void setProduction(double production) {
        this.production = production;
    }
}
