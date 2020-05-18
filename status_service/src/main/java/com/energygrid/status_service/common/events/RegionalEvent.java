package com.energygrid.status_service.common.events;

import com.energygrid.status_service.common.models.Consumption;
import com.energygrid.status_service.common.models.Production;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("regional_event")
public class RegionalEvent extends AbstractEvent {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumption_id", referencedColumnName = "id")
    @JsonProperty
    private Consumption consumption;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "production_id", referencedColumnName = "id")
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
