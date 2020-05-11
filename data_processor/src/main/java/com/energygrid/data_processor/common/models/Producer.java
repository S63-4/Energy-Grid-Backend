package com.energygrid.data_processor.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Producer {

    @JsonProperty
    private String name;
    @JsonProperty
    private double production;

    public Producer(String name, double production) {
        this.name = name;
        this.production = production;
    }

    public Producer() {
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
