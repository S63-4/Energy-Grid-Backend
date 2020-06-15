package com.energygrid.status_service.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consumer_group")
public class ConsumerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "total_consumers")
    @JsonProperty
    private int totalConsumers;
    @Column(name = "total_consumption")
    @JsonProperty
    private double totalConsumption;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumer_id")
    @JsonProperty
    private List<Consumer> consumers;

    @ManyToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private Consumption consumption;

//    @OneToOne(mappedBy = "households")
//    private Consumption householdsConsumption;
//
//    @OneToOne(mappedBy = "bigConsumers")
//    private Consumption bigConsumersConsumption;
//
//    @OneToOne(mappedBy = "industries")
//    private Consumption industriesConsumption;

    public ConsumerGroup(int totalConsumers, double totalConsumption, List<Consumer> consumers) {
        this.totalConsumers = totalConsumers;
        this.totalConsumption = totalConsumption;
        this.consumers = consumers;
    }

    public ConsumerGroup() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalConsumers() {
        return totalConsumers;
    }

    public void setTotalConsumers(int totalConsumers) {
        this.totalConsumers = totalConsumers;
    }

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public <T extends Consumer> void setConsumers(List<T> consumers) {
        this.consumers = (List<Consumer>) consumers;
    }

}
