package com.energygrid.data_processor.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Production {

    @JsonProperty("wind_farms")
    private ProducerGroup windFarms;
    @JsonProperty("solar_farms")
    private ProducerGroup solarFarms;
    @JsonProperty("power_plants")
    private ProducerGroup powerPlants;
    @JsonProperty("households")
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
