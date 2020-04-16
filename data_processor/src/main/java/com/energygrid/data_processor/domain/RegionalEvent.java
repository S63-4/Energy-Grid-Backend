package com.energygrid.data_processor.domain;

import java.time.LocalDateTime;

public class RegionalEvent extends AbstractEvent {

    public RegionalEvent(LocalDateTime dateTime) {
        super(dateTime);
    }
}
