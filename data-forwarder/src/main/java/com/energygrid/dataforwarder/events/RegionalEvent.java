package com.energygrid.dataforwarder.events;

import com.energygrid.dataforwarder.events.models.Consumption;
import com.energygrid.dataforwarder.events.models.Production;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RegionalEvent extends AbstractEvent {

    @JsonProperty
    private Consumption consumption;
    @JsonProperty
    private Production production;

    public RegionalEvent(LocalDateTime dateTime, String region) {
        super(dateTime, region);
    }

    public RegionalEvent() {
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
