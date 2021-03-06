package com.energygrid.datarestforwarder.consumers;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

@Configuration
public class MessageConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("simulator-exchange");
    }

    private static class ConsumerConfig {

        // Queue for regional production
        @Bean
        public Queue regionalSimQueue() {
            return new AnonymousQueue();
        }

        // Queue for national production
        @Bean
        public Queue nationalSimQueue() {
            return new AnonymousQueue();
        }

        // Queue for the market
        @Bean
        public Queue marketSimQueue() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding bindingRegional(DirectExchange directExchangeSimulator, Queue regionalSimQueue) {
            return BindingBuilder.bind(regionalSimQueue).to(directExchangeSimulator).with("regional");
        }

        @Bean
        public Binding bindingNational(DirectExchange directExchangeSimulator, Queue nationalSimQueue) {
            return BindingBuilder.bind(nationalSimQueue).to(directExchangeSimulator).with("national");
        }

        @Bean
        public Binding bindingMarket(DirectExchange directExchangeSimulator, Queue marketSimQueue) {
            return BindingBuilder.bind(marketSimQueue).to(directExchangeSimulator).with("market");
        }

        @Bean
        public MessageConsumer consumer() {
            return new MessageConsumer();
        }

    }
}
