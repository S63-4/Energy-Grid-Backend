package com.energygrid.status_service.common.models;

import com.energygrid.status_service.common.events.RegionalEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "production")
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producer_group_windfarms_id", referencedColumnName = "id")
    @JsonProperty
    private ProducerGroup windFarms;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producer_group_solarfarms_id", referencedColumnName = "id")
    @JsonProperty
    private ProducerGroup solarFarms;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producer_group_powerplants_id", referencedColumnName = "id")
    @JsonProperty
    private ProducerGroup powerPlants;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producer_group_households_id", referencedColumnName = "id")
    @JsonProperty
    private ProducerGroup households;

    @OneToOne(mappedBy = "production")
    private RegionalEvent regionalEvent;

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
