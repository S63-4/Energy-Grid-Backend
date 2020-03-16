package com.energygrid.common.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private double consumption;

    @Column(nullable = false)
    private double production;



    public Status(Date date, double consumption, double production) {
        this.date = date;
        this.consumption = consumption;
        this.production = production;
    }

    public Status() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date localDate) {
        this.date = localDate;
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
