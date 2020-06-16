package com.energygrid.status_service.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@Table(name = "household_producer")
public class HouseholdProducer extends Producer {

    @Column(name = "num_connections")
    @JsonProperty
    private int numConnections;

    public HouseholdProducer(String name, double production, int numConnections) {
        super(name, production);
        this.numConnections = numConnections;
    }

    public HouseholdProducer() {
    }

    public int getNumConnections() {
        return numConnections;
    }

    public void setNumConnections(int numConnections) {
        this.numConnections = numConnections;
    }
}
