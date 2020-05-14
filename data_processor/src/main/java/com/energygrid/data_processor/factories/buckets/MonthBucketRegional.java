package com.energygrid.data_processor.factories.buckets;

import java.time.LocalDateTime;

public class MonthBucketRegional extends AbstractBucket {

    public MonthBucketRegional(LocalDateTime dateTime) {
        super(dateTime);
    }
}
