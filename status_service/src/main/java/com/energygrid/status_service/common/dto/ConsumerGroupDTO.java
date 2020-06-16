package com.energygrid.status_service.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConsumerGroupDTO {

    @JsonProperty("num_consumers")
    private int totalConsumers;
    @JsonProperty("total_consumption")
    private double totalConsumption;
    @JsonProperty("consumers")
    private List<ConsumerDTO> consumers;

    public ConsumerGroupDTO() {

    }

    public ConsumerGroupDTO(int totalConsumers, double totalConsumption, List<ConsumerDTO> consumers) {
        this.totalConsumers = totalConsumers;
        this.totalConsumption = totalConsumption;
        this.consumers = consumers;
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

    public List<ConsumerDTO> getConsumers() {
        return consumers;
    }

    public <T extends ConsumerDTO> void setConsumers(List<T> consumers) {
        this.consumers = (List<ConsumerDTO>) consumers;
    }
}
