package com.energygrid.status_service.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@Table(name = "household_consumer")
public class HouseholdConsumer extends Consumer {

    @Column(name = "num_connections")
    @JsonProperty
    private int numConnections;

    public HouseholdConsumer(String name, double consumption, int numConnections) {
        super(name, consumption);
        this.numConnections = numConnections;
    }

    public HouseholdConsumer() {
    }

    public int getNumConnections() {
        return numConnections;
    }

    public void setNumConnections(int numConnections) {
        this.numConnections = numConnections;
    }
}
