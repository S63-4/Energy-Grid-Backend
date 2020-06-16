package com.energygrid.status_service.common.models;

import com.energygrid.status_service.common.events.RegionalEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "consumption")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private List<ConsumerGroup> groups;

    @OneToOne(mappedBy = "consumption")
    private RegionalEvent regionalEvent;

    public Consumption() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ConsumerGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ConsumerGroup> groups) {
        this.groups = groups;
    }

    public RegionalEvent getRegionalEvent() {
        return regionalEvent;
    }

    public void setRegionalEvent(RegionalEvent regionalEvent) {
        this.regionalEvent = regionalEvent;
    }
}
