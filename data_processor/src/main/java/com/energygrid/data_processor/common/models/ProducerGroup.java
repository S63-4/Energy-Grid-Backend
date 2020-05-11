package com.energygrid.data_processor.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ProducerGroup {

    @JsonProperty("num_producers")
    private int totalProducers;
    @JsonProperty("total_production")
    private double totalProduction;
    @JsonProperty("producers")
    private ArrayList<Producer> producers;

    public ProducerGroup(int totalProducers, double totalProduction, ArrayList<Producer> producers) {
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

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public <T extends Producer> void setProducers(ArrayList<T> producers) {
        this.producers = (ArrayList<Producer>) producers;
    }
}
