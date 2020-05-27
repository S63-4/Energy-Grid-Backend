package com.energygrid.status_service.common.models;

import com.energygrid.status_service.common.events.RegionalEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "consumption")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumption_id")
    private List<ConsumerGroup> groups = new ArrayList<>();

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "consumer_group_households_id", referencedColumnName = "id")
//    @JsonProperty("households")
//    private ConsumerGroup households;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "consumer_group_bigconsumers_id", referencedColumnName = "id")
//    @JsonProperty("big_consumers")
//    private ConsumerGroup bigConsumers;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "consumer_group_industries_id", referencedColumnName = "id")
//    @JsonProperty("industries")
//    private  ConsumerGroup industries;

    @OneToOne(mappedBy = "consumption")
    private RegionalEvent regionalEvent;

//    public Consumption(ConsumerGroup households, ConsumerGroup bigConsumers, ConsumerGroup industries) {
//        this.households = households;
//        this.bigConsumers = bigConsumers;
//        this.industries = industries;
//    }

    public Consumption() {
    }


//    public ConsumerGroup getHouseholds() {
//        return households;
//    }
//
//    public void setHouseholds(ConsumerGroup households) {
//        this.households = households;
//    }
//
//    public ConsumerGroup getBigConsumers() {
//        return bigConsumers;
//    }
//
//    public void setBigConsumers(ConsumerGroup bigConsumers) {
//        this.bigConsumers = bigConsumers;
//    }
//
//    public ConsumerGroup getIndustries() {
//        return industries;
//    }
//
//    public void setIndustries(ConsumerGroup industries) {
//        this.industries = industries;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ConsumerGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ConsumerGroup> groups) {
        this.groups = groups;
    }

    public RegionalEvent getRegionalEvent() {
        return regionalEvent;
    }

    public void setRegionalEvent(RegionalEvent regionalEvent) {
        this.regionalEvent = regionalEvent;
    }
}
