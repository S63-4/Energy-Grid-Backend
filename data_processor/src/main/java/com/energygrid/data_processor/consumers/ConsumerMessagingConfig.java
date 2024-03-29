package com.energygrid.data_processor.consumers;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerMessagingConfig {

    @Bean("simulator-exchange")
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
        public Binding bindingRegional(@Qualifier("simulator-exchange") DirectExchange directExchangeSimulator, Queue regionalSimQueue) {
            return BindingBuilder.bind(regionalSimQueue).to(directExchangeSimulator).with("regional");
        }

        @Bean
        public Binding bindingNational(@Qualifier("simulator-exchange")DirectExchange directExchangeSimulator, Queue nationalSimQueue) {
            return BindingBuilder.bind(nationalSimQueue).to(directExchangeSimulator).with("national");
        }

        @Bean
        public Binding bindingMarket(@Qualifier("simulator-exchange") DirectExchange directExchangeSimulator, Queue marketSimQueue) {
            return BindingBuilder.bind(marketSimQueue).to(directExchangeSimulator).with("market");
        }

        @Bean
        public MessageConsumer consumer() {
            return new MessageConsumer();
        }
    }
}
