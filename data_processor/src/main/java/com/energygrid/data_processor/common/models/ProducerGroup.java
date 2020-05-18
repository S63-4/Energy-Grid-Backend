package com.energygrid.data_processor.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ProducerGroup {

    @JsonProperty("num_producers")
    private int totalProducers;
    @JsonProperty("total_production")
    private double totalProduction;
    @JsonProperty("producers")
    private List<Producer> producers;

    public ProducerGroup(int totalProducers, double totalProduction, List<Producer> producers) {
        this.totalProducers = totalProducers;
        this.totalProduction = totalProduction;
        this.producers = producers;
    }

    public ProducerGroup() {

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

    public List<Producer> getProducers() {
        return producers;
    }

    public <T extends Producer> void setProducers(List<T> producers) {
        this.producers = (List<Producer>) producers;
    }
}
