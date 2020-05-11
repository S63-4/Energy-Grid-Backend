package com.energygrid.data_processor.factories.buckets;

import java.time.LocalDateTime;

public class MonthBucket {

    private LocalDateTime dateTime;

    public MonthBucket(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
