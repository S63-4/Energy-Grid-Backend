package com.energygrid.data_processor.producers;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

public class MessageProducer {

    @Autowired
    private RabbitTemplate template;

    @Qualifier("data-processor-exchange")
    @Autowired
    private DirectExchange directExchange;

    public void sendRegionalHourMessage(String message) {
        template.convertAndSend(directExchange.getName(), "regional-hour", message);
    }

    public void sendRegionalDayMessage(String message) {
        template.convertAndSend(directExchange.getName(), "regional-day", message);
    }

    public void sendRegionalMonthMessage(String message) {
        template.convertAndSend(directExchange.getName(), "regional-month", message);
    }
}
