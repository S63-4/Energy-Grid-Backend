package com.energygrid.status_service.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProducerGroupDTO {

    @JsonProperty("num_producers")
    private int totalProducers;
    @JsonProperty("total_production")
    private double totalProduction;
    @JsonProperty("producers")
    private List<ProducerDTO> producers;

    public ProducerGroupDTO() {

    }

    public ProducerGroupDTO(int totalProducers, double totalProduction, List<ProducerDTO> producers) {
        this.totalProducers = totalProducers;
        this.totalProduction = totalProduction;
        this.producers = producers;
    }

    public int getTotalProducers() {
        return totalProducers;
    }

    public void setTotalProducers(int totalProducers) {
        this.totalProducers = totalProducers;
    }

    public double getTotalProduction() {
        return totalProduction;
    }

    public void setTotalProduction(double totalProduction) {
        this.totalProduction = totalProduction;
    }

    public List<ProducerDTO> getProducers() {
        return producers;
    }

    public <T extends ProducerDTO> void setProducers(List<T> producers) {
        this.producers = (List<ProducerDTO>) producers;
    }
}
