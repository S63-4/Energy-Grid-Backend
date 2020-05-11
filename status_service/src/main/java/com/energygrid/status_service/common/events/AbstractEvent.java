package com.energygrid.status_service.common.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public abstract class AbstractEvent {

    @JsonProperty
    private LocalDateTime localDateTime;
    @JsonProperty
    private String region;

    public AbstractEvent(LocalDateTime localDateTime, String region) {
        this.localDateTime = localDateTime;
        this.region = region;
    }

    public AbstractEvent() {
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
