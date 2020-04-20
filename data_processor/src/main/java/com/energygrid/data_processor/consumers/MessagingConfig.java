package com.energygrid.data_processor.consumers;

import com.energygrid.data_processor.consumers.MessageConsumer;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("simulator");
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
        public Binding bindingRegional(DirectExchange directExchange, Queue regionalSimQueue) {
            return BindingBuilder.bind(regionalSimQueue).to(directExchange).with("regional");
        }

        @Bean
        public Binding bindingNational(DirectExchange directExchange, Queue nationalSimQueue) {
            return BindingBuilder.bind(nationalSimQueue).to(directExchange).with("national");
        }

        @Bean
        public Binding bindingMarket(DirectExchange directExchange, Queue marketSimQueue) {
            return BindingBuilder.bind(marketSimQueue).to(directExchange).with("market");
        }

        @Bean
        public MessageConsumer consumer() {
            return new MessageConsumer();
        }
    }
}
