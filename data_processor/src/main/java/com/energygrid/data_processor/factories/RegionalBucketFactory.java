package com.energygrid.data_processor.factories;

import com.energygrid.data_processor.factories.buckets.DayBucketRegional;
import com.energygrid.data_processor.factories.buckets.HourBucketRegional;
import com.energygrid.data_processor.factories.buckets.MonthBucketRegional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegionalBucketFactory {

    private MonthBucketRegional monthBucket;
    private DayBucketRegional dayBucket;
    private HourBucketRegional hourBucket;

    public HourBucketRegional getHourBucket(LocalDateTime dateTime) {
        if (hourBucket == null) {
            this.hourBucket = new HourBucketRegional(dateTime);
        }
        return hourBucket;
    }

    public DayBucketRegional getDayBucket(LocalDateTime dateTime) {
        if (dayBucket == null) {
            this.dayBucket = new DayBucketRegional(dateTime);
        }
        return dayBucket;
    }

    public MonthBucketRegional getMonthBucket(LocalDateTime dateTime) {
        if (monthBucket == null) {
            this.monthBucket = new MonthBucketRegional(dateTime);
        }
        return monthBucket;
    }

    public HourBucketRegional newHourBucket(LocalDateTime dateTime) {
        this.hourBucket = new HourBucketRegional(dateTime);
        return this.hourBucket;
    }

    public DayBucketRegional newDayBucket(LocalDateTime dateTime) {
        this.dayBucket = new DayBucketRegional(dateTime);
        return this.dayBucket;
    }

    public MonthBucketRegional newMonthBucket(LocalDateTime dateTime) {
        this.monthBucket = new MonthBucketRegional(dateTime);
        return this.monthBucket;
    }
}
