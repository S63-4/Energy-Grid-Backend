package com.energygrid.data_processor.producers;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerMessagingConfig {
    @Bean(name = "data-processor-exchange")
    public DirectExchange directExchange() {
        return new DirectExchange("data-processor-exchange");
    }

    @Bean
    public MessageProducer producer() {
        return new MessageProducer();
    }
}
