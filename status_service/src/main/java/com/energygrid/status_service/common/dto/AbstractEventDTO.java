package com.energygrid.status_service.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public abstract class AbstractEventDTO {

    @JsonProperty
    private LocalDateTime localDateTime;
    @JsonProperty
    private String region;

    public AbstractEventDTO() {
    }

    public AbstractEventDTO(LocalDateTime localDateTime, String region) {
        this.localDateTime = localDateTime;
        this.region = region;
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
