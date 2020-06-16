package com.energygrid.status_service.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsumerDTO {

    @JsonProperty
    private String name;
    @JsonProperty
    private double consumption;

    public ConsumerDTO() {

    }

    public ConsumerDTO(String name, double consumption) {
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
