package com.energygrid.status_service.consumers;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerMessagingConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("data-processor-exchange");
    }

    private static class ConsumerConfig {
        @Bean
        public Queue regionalHourQueue() {
            return new AnonymousQueue();
    }

        @Bean
        public Binding saveStatusBinding (DirectExchange directExchange, Queue regionalHourQueue){
            return BindingBuilder.bind(regionalHourQueue).to(directExchange).with("regional-hour");
    }

        @Bean
        public MessageConsumer messageConsumer () {
            return new MessageConsumer();
        }
    }
}
