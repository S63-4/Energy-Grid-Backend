package com.energygrid.datarestforwarder.events;

import com.energygrid.datarestforwarder.models.Consumption;
import com.energygrid.datarestforwarder.models.Production;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Date;

public class RegionalEvent extends AbstractEvent {

    @JsonProperty
    private Consumption consumption;
    @JsonProperty
    private Production production;

    public RegionalEvent() {
    }

    public RegionalEvent(String date, String region) {
        super(date, region);
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
