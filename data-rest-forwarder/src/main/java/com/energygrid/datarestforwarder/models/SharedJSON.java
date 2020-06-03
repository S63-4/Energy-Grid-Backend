package com.energygrid.datarestforwarder.models;

import java.time.LocalDateTime;

public class SharedJSON {
    LocalDateTime dateTime;
    String region;
    double consumption;
    double production;

    public SharedJSON() {
        this.region = "Zeeland";
    }

    public SharedJSON(LocalDateTime dateTime, double consumption, double production) {
        this.dateTime = dateTime;
        this.region = "Zeeland";
        this.consumption = consumption;
        this.production = production;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getRegion() {
        return region;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getProduction() {
        return production;
    }

    public void setProduction(double production) {
        this.production = production;
    }
}
