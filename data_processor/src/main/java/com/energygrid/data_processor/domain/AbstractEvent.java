package com.energygrid.data_processor.domain;

import java.time.LocalDateTime;

public abstract class AbstractEvent {
    private LocalDateTime dateTime;

    public AbstractEvent(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
