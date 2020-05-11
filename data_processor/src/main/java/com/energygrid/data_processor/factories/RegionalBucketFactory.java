package com.energygrid.data_processor.factories;

import com.energygrid.data_processor.factories.buckets.DayBucketRegional;
import com.energygrid.data_processor.factories.buckets.HourBucketRegional;
import com.energygrid.data_processor.factories.buckets.MonthBucketRegional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BucketFactory {

    private MonthBucketRegional monthBucketRegional;
    private DayBucketRegional dayBucketRegional;
    private HourBucketRegional hourBucketRegional;

    public HourBucketRegional getHourBucketRegional() {
        if (hourBucketRegional == null) {
            this.hourBucketRegional = new HourBucketRegional();
        }
        return hourBucketRegional;
    }

    public DayBucketRegional getDayBucketRegional() {
        if (dayBucketRegional == null) {
            this.dayBucketRegional = new DayBucketRegional();
        }
        return dayBucketRegional;
    }

    public MonthBucketRegional getMonthBucketRegional() {
        if (monthBucketRegional == null) {
            this.monthBucketRegional = new MonthBucketRegional();
        }
        return monthBucketRegional;
    }

    public HourBucketRegional newHourBucketRegional(LocalDateTime dateTime) {
        this.hourBucketRegional = new HourBucketRegional(dateTime);
        return this.hourBucketRegional;
    }
}
