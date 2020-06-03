package com.energygrid.data_processor.factories.buckets;

import java.time.LocalDateTime;

public class DayBucketRegional extends AbstractBucket {

    public DayBucketRegional(LocalDateTime dateTime) {
        super(dateTime);
    }
}
