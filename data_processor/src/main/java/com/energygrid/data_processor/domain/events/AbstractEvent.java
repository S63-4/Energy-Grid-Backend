package com.energygrid.data_processor.domain.events;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractEvent {

    @JsonProperty
    private String date;
    @JsonProperty
    private String region;

    public AbstractEvent(String date, String region) {
        this.date = date;
        this.region = region;
    }

    public AbstractEvent() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
