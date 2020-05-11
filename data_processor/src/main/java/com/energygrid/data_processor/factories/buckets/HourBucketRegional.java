package com.energygrid.data_processor.factories.buckets;

import com.energygrid.data_processor.common.events.AbstractEvent;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class HourBucket {

    private LocalDateTime dateTime;
    private AbstractEvent events = null;

    public HourBucket(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void addToBucket() {

    }
}
