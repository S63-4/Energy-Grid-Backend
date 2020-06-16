package com.energygrid.status_service.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public interface ConsumerWithDateTime {
    String getName();
    double getConsumption();
    LocalDateTime getLocalDateTime();
}
