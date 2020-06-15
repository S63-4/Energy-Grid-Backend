package com.energygrid.data_processor.factories.buckets;

import com.energygrid.data_processor.common.events.AbstractEvent;
import com.energygrid.data_processor.common.events.RegionalEvent;
import com.energygrid.data_processor.common.models.Consumer;
import com.energygrid.data_processor.common.models.ConsumerGroup;
import com.energygrid.data_processor.common.models.Consumption;
import com.energygrid.data_processor.common.models.HouseholdConsumer;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class HourBucketRegional extends AbstractBucket {

    private RegionalEvent event = null;

    public HourBucketRegional(LocalDateTime dateTime) {
        super(dateTime);
    }

    public void add(RegionalEvent newEvent) {
        if (this.event == null) {
            this.event = newEvent;
        }
        else {
            // For tracking purposes, the datetime in the bucketEvent will update with the dateTime in the newEvent
            //this.event.setLocalDateTime(newEvent.getLocalDateTime());
            // Currently this code checks if the entry at index i in the bucket has the same zip-code as the entry at index i in the incoming event
            // If this is the case, the 2 are summed up as the new total. If not, the matching zip-code will be looked for in the hashmap.
            // If there is no match in the incoming event, the current total will remain standing.
            // WARNING
            // - code is not checking to update the total_consumers and num_connections fields and will not add consumers not present in old event.
            final long startTime = System.currentTimeMillis();
            ConsumerGroup bucketEventHouseholdGroup = this.event.getConsumption().getHouseholds();
            ConsumerGroup newEventHouseholdGroup = newEvent.getConsumption().getHouseholds();
            List<Consumer> bucketConsumers = bucketEventHouseholdGroup.getConsumers();
            List<Consumer> newConsumers = newEventHouseholdGroup.getConsumers();
            HashMap<String, Double> newConsumersConsumption = new HashMap<>();
            for(Consumer consumer : newConsumers) {
                newConsumersConsumption.put(consumer.getName(), consumer.getConsumption());
            }
            int length = Math.max(bucketConsumers.size(), newConsumers.size());
            for(int i= 0; i < length; i++) {
                // If entries at same index have same zip-code, add them together
                if (bucketConsumers.get(i).getName().equals(newConsumers.get(i).getName())) {
                    bucketConsumers.get(i).setConsumption(bucketConsumers.get(i).getConsumption() + newConsumers.get(i).getConsumption());
                } else {
                    // If same zip-code cannot be found at the same index, look for it in hashmap.
                    String zipCode = bucketConsumers.get(i).getName();
                    Double newConsumption = newConsumersConsumption.get(zipCode);
                    if (newConsumption != null) {
                        bucketConsumers.get(i).setConsumption(bucketConsumers.get(i).getConsumption() + newConsumption);
                    }
                }
            }
            this.event.getConsumption().getHouseholds().setTotalConsumption(bucketEventHouseholdGroup.getTotalConsumption() + newEventHouseholdGroup.getTotalConsumption());
            final long endTime = System.currentTimeMillis();
            System.out.println("Execution time: " + (endTime-startTime) + "ms");
        }
    }

    public RegionalEvent getEvent() {
        return event;
    }

    public boolean isExpired(LocalDateTime dateTime) {
        // before comparing hours in dateTimes, minutes have to be stripped.
        dateTime = dateTime.minusMinutes(dateTime.getMinute());
        return !dateTime.isEqual(this.getDateTime());
    }
}
