package com.energygrid.datarestforwarder.models;

import java.time.LocalDateTime;

public class SharedJSON {
    String date;
    String region;
    double consumption;
    double production;

    public SharedJSON() {
        this.region = "Zeeland";
    }

    public SharedJSON(String date, double consumption, double production) {
        this.date = date;
        this.region = "Zeeland";
        this.consumption = consumption;
        this.production = production;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
