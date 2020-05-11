package com.energygrid.data_processor.factories.buckets;

import java.time.LocalDateTime;

public class DayBucket {

    private LocalDateTime dateTime;

    public DayBucket(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
