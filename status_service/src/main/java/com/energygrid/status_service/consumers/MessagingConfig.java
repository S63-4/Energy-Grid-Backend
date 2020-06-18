package com.energygrid.status_service.consumers;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean("data-processor-exchange")
    public DirectExchange directExchange() {
        return new DirectExchange("data-processor-exchange");
    }

    @Bean("user-service-exchange")
    public DirectExchange directExchange2() {
        return new DirectExchange("user-service-exchange");
    }

    private static class ConsumerConfig {

        @Bean
        public Queue regionalHourQueue() {
            return new AnonymousQueue();
    }

        @Bean
        public Binding saveStatusBinding (@Qualifier("data-processor-exchange") DirectExchange directExchange, Queue regionalHourQueue){
            return BindingBuilder.bind(regionalHourQueue).to(directExchange).with("regional-hour");
        }

        @Bean
        public MessageConsumer messageConsumer () {
            return new MessageConsumer();
        }
    }

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }
}
