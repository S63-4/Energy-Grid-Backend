package com.energygrid.datarestforwarder.events;

import com.energygrid.datarestforwarder.models.Consumption;
import com.energygrid.datarestforwarder.models.Production;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RegionalEvent extends AbstractEvent {

    @JsonProperty
    private Consumption consumption;
    @JsonProperty
    private Production production;

    public RegionalEvent() {
    }

    public RegionalEvent(LocalDateTime dateTime, String region) {
        super(dateTime, region);
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }
}
