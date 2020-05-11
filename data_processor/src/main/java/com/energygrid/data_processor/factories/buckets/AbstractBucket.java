package com.energygrid.data_processor.factories.buckets;

import java.time.LocalDateTime;

public class AbstractBucket {

    private LocalDateTime dateTime;

    public AbstractBucket(LocalDateTime dateTime) {
        dateTime = dateTime.minusMinutes(dateTime.getMinute());
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
