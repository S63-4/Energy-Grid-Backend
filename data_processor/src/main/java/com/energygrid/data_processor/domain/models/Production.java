package com.energygrid.data_processor.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Production {

    @JsonProperty
    private ProducerGroup windFarms;
    @JsonProperty
    private ProducerGroup solarFarms;
    @JsonProperty
    private ProducerGroup powerPlants;
    @JsonProperty
    private ProducerGroup households;

    public Production(ProducerGroup windFarms, ProducerGroup solarFarms, ProducerGroup powerPlants, ProducerGroup households) {
        this.windFarms = windFarms;
        this.solarFarms = solarFarms;
        this.powerPlants = powerPlants;
        this.households = households;
    }

    public Production() {

    }

    public ProducerGroup getWindFarms() {
        return windFarms;
    }

    public void setWindFarms(ProducerGroup windFarms) {
        this.windFarms = windFarms;
    }

    public ProducerGroup getSolarFarms() {
        return solarFarms;
    }

    public void setSolarFarms(ProducerGroup solarFarms) {
        this.solarFarms = solarFarms;
    }

    public ProducerGroup getPowerPlants() {
        return powerPlants;
    }

    public void setPowerPlants(ProducerGroup powerPlants) {
        this.powerPlants = powerPlants;
    }

    public ProducerGroup getHouseholds() {
        return households;
    }

    public void setHouseholds(ProducerGroup households) {
        this.households = households;
    }
}
