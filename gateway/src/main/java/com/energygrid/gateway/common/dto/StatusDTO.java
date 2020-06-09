package com.energygrid.gateway.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class StatusDTO {

    @JsonProperty
    private Date date;

    @JsonProperty
    private double consumption;

    @JsonProperty
    private double production;

    @JsonProperty
    private String label;

    public StatusDTO(Date date, int consumption, int production) {
        this.date = date;
        this.consumption = consumption;
        this.production = production;
    }

    public StatusDTO() {
    }

    public StatusDTO(String label) {
        this.label = label;
    }

    @JsonIgnore
    public LocalDate getLocalDate() {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public void addConsumption(double addition) {
        this.consumption += addition;
    }

    public double getProduction() {
        return production;
    }

    public void setProduction(double production) {
        this.production = production;
    }

    public void addProduction(double addition) {
        this.production += addition;
    }
}
