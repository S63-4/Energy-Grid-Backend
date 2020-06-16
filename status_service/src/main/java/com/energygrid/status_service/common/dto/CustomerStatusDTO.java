package com.energygrid.status_service.common.dto;


public class CustomerStatusDTO {

    private String dateTime;
    private String name;
    private double consumption;
    private double production;

    public CustomerStatusDTO(String dateTime, String name, double consumption, double production) {
        this.dateTime = dateTime;
        this.name = name;
        this.consumption = consumption;
        this.production = production;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
