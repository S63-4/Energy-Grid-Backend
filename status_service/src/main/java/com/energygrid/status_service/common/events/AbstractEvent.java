package com.energygrid.status_service.common.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "event_type")
@Table(name = "event")
public abstract class AbstractEvent {

    @Id
    @Column(name = "date_time")
    @JsonProperty
    private LocalDateTime localDateTime;
    @Column(name = "region")
    @JsonProperty
    private String region;
    @Column(name = "period")
    private String period;

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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
