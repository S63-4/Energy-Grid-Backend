package com.energygrid.status_service.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionDTO {

    @JsonProperty("wind_farms")
    private ProducerGroupDTO windFarms;
    @JsonProperty("solar_farms")
    private ProducerGroupDTO solarFarms;
    @JsonProperty("power_plants")
    private ProducerGroupDTO powerPlants;
    @JsonProperty("households")
    private ProducerGroupDTO households;

    public ProductionDTO() {

    }

    public ProductionDTO(ProducerGroupDTO windFarms, ProducerGroupDTO solarFarms, ProducerGroupDTO powerPlants, ProducerGroupDTO households) {
        this.windFarms = windFarms;
        this.solarFarms = solarFarms;
        this.powerPlants = powerPlants;
        this.households = households;
    }

    public ProducerGroupDTO getWindFarms() {
        return windFarms;
    }

    public void setWindFarms(ProducerGroupDTO windFarms) {
        this.windFarms = windFarms;
    }

    public ProducerGroupDTO getSolarFarms() {
        return solarFarms;
    }

    public void setSolarFarms(ProducerGroupDTO solarFarms) {
        this.solarFarms = solarFarms;
    }

    public ProducerGroupDTO getPowerPlants() {
        return powerPlants;
    }

    public void setPowerPlants(ProducerGroupDTO powerPlants) {
        this.powerPlants = powerPlants;
    }

    public ProducerGroupDTO getHouseholds() {
        return households;
    }

    public void setHouseholds(ProducerGroupDTO households) {
        this.households = households;
    }
}
