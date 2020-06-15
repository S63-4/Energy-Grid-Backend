package com.energygrid.status_service.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "producer")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    @JsonProperty
    private String name;
    @Column(name = "production")
    @JsonProperty
    private double production;

    @ManyToOne
    @JoinColumn(name = "producer_id", insertable = false, updatable = false)
    private ProducerGroup producerGroup;

    public Producer(String name, double production) {
        this.name = name;
        this.production = production;
    }

    public Producer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProduction() {
        return production;
    }

    public void setProduction(double production) {
        this.production = production;
    }
}
