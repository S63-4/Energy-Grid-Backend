package com.energygrid.data_processor.factories.buckets;

import com.energygrid.data_processor.common.events.AbstractEvent;
import com.energygrid.data_processor.common.events.RegionalEvent;
import com.energygrid.data_processor.common.models.*;

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
            // this.event.setLocalDateTime(newEvent.getLocalDateTime());
            // Currently this code checks if the entry at index i in the bucket has the same zip-code as the entry at index i in the incoming event
            // If this is the case, the 2 are summed up as the new total. If not, the matching zip-code will be looked for in the hashmap.
            // If there is no match in the incoming event, the current total will remain standing.
            // WARNING
            // - code is not checking to update the total_consumers and num_connections fields and will not add consumers not present in old event.
            final long startTime = System.currentTimeMillis();
            this.sumUpHouseholdConsumption(newEvent);
            this.sumUpBigConsumerConsumption(newEvent);
            this.sumUpIndustriesConsumption(newEvent);
            this.sumUpWindFarmProduction(newEvent);
            this.sumUpSolarFarmProduction(newEvent);
            this.sumUpPowerPlantProduction(newEvent);
            final long endTime = System.currentTimeMillis();
            System.out.println("Execution time: " + (endTime-startTime) + "ms");
        }
    }

    private void sumUpHouseholdConsumption(RegionalEvent newEvent) {
        ConsumerGroup bucketEventGroup = this.event.getConsumption().getHouseholds();
        ConsumerGroup newEventGroup = newEvent.getConsumption().getHouseholds();
        List<Consumer> bucketConsumers = bucketEventGroup.getConsumers();
        List<Consumer> newConsumers = newEventGroup.getConsumers();
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
        this.event.getConsumption().getHouseholds().setTotalConsumption(bucketEventGroup.getTotalConsumption() + newEventGroup.getTotalConsumption());
    }

    private void sumUpBigConsumerConsumption(RegionalEvent newEvent) {
        ConsumerGroup bucketEventGroup = this.event.getConsumption().getBigConsumers();
        ConsumerGroup newEventGroup = newEvent.getConsumption().getBigConsumers();
        List<Consumer> bucketConsumers = bucketEventGroup.getConsumers();
        List<Consumer> newConsumers = newEventGroup.getConsumers();
        this.event.getConsumption().getBigConsumers().setConsumers(sumUpConsumerLists(bucketConsumers, newConsumers));
        this.event.getConsumption().getBigConsumers().setTotalConsumption(bucketEventGroup.getTotalConsumption() + newEventGroup.getTotalConsumption());
    }

    private void sumUpIndustriesConsumption(RegionalEvent newEvent) {
        ConsumerGroup bucketEventGroup = this.event.getConsumption().getIndustries();
        ConsumerGroup newEventGroup = newEvent.getConsumption().getIndustries();
        List<Consumer> bucketConsumers = bucketEventGroup.getConsumers();
        List<Consumer> newConsumers = newEventGroup.getConsumers();
        this.event.getConsumption().getIndustries().setConsumers(sumUpConsumerLists(bucketConsumers, newConsumers));
        this.event.getConsumption().getIndustries().setTotalConsumption(bucketEventGroup.getTotalConsumption() + newEventGroup.getTotalConsumption());
    }

    private void sumUpWindFarmProduction(RegionalEvent newEvent) {
        ProducerGroup bucketEventGroup = this.event.getProduction().getWindFarms();
        ProducerGroup newEventGroup = newEvent.getProduction().getWindFarms();
        List<Producer> bucketProducers = bucketEventGroup.getProducers();
        List<Producer> newProducers = newEventGroup.getProducers();
        this.event.getProduction().getWindFarms().setProducers(sumUpProducerLists(bucketProducers, newProducers));
        this.event.getProduction().getWindFarms().setTotalProduction(bucketEventGroup.getTotalProduction() + newEventGroup.getTotalProduction());
    }

    private void sumUpSolarFarmProduction(RegionalEvent newEvent) {
        ProducerGroup bucketEventGroup = this.event.getProduction().getSolarFarms();
        ProducerGroup newEventGroup = newEvent.getProduction().getSolarFarms();
        List<Producer> bucketProducers = bucketEventGroup.getProducers();
        List<Producer> newProducers = newEventGroup.getProducers();
        this.event.getProduction().getSolarFarms().setProducers(sumUpProducerLists(bucketProducers, newProducers));
        this.event.getProduction().getSolarFarms().setTotalProduction(bucketEventGroup.getTotalProduction() + newEventGroup.getTotalProduction());
    }

    private void sumUpPowerPlantProduction(RegionalEvent newEvent) {
        ProducerGroup bucketEventGroup = this.event.getProduction().getPowerPlants();
        ProducerGroup newEventGroup = newEvent.getProduction().getPowerPlants();
        List<Producer> bucketProducers = bucketEventGroup.getProducers();
        List<Producer> newProducers = newEventGroup.getProducers();
        this.event.getProduction().getPowerPlants().setProducers(sumUpProducerLists(bucketProducers, newProducers));
        this.event.getProduction().getPowerPlants().setTotalProduction(bucketEventGroup.getTotalProduction() + newEventGroup.getTotalProduction());
    }

    private List<Consumer> sumUpConsumerLists(List<Consumer> listA, List<Consumer> listB) {
        int length = Math.max(listA.size(), listB.size());
        for(int i=0; i<length; i++) {
//            if (listA.get(i).getName().equals(ListB.get(i).getName())) {
            listA.get(i).setConsumption(listA.get(i).getConsumption() + listB.get(i).getConsumption());
//            }
        }
        return listA;
    }

    private List<Producer> sumUpProducerLists(List<Producer> listA, List<Producer> listB) {
        int length = Math.max(listA.size(), listB.size());
        for(int i=0; i<length; i++) {
//            if (listA.get(i).getName().equals(ListB.get(i).getName())) {
            listA.get(i).setProduction(listA.get(i).getProduction() + listB.get(i).getProduction());
//            }
        }
        return listA;
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
