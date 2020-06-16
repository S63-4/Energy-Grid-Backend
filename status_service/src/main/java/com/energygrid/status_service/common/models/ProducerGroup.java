package com.energygrid.status_service.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producer_group")
public class ProducerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "total_producers")
    @JsonProperty
    private int totalProducers;
    @Column(name = "total_production")
    @JsonProperty
    private double totalProduction;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "producer_id")
    @JsonProperty
    private List<Producer> producers;


    public ProducerGroup(int totalProducers, double totalProduction, List<Producer> producers) {
        this.totalProducers = totalProducers;
        this.totalProduction = totalProduction;
        this.producers = producers;
    }

    public ProducerGroup() {

    }

    public int getTotalProducers() {
        return totalProducers;
    }

    public void setTotalProducers(int totalProducers) {
        this.totalProducers = totalProducers;
    }

    public double getTotalProduction() {
        return totalProduction;
    }

    public void setTotalProduction(double totalProduction) {
        this.totalProduction = totalProduction;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public <T extends Producer> void setProducers(List<T> producers) {
        this.producers = (List<Producer>) producers;
    }
}
