package com.energygrid.status_service.common.models;

import com.energygrid.status_service.common.events.RegionalEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "production")
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private List<ProducerGroup> groups;

    public Production() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProducerGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ProducerGroup> groups) {
        this.groups = groups;
    }
}
