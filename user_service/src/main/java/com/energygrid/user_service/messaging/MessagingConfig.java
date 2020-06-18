package com.energygrid.user_service.messaging;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("user-service-queue");
    }

    public static class ConsumerConfig {

        @Bean
        public Queue zipCodeQueue() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding zipCodeBinding(DirectExchange directExchange, Queue zipCodeQueue ) {
            return BindingBuilder.bind(zipCodeQueue).to(directExchange).with("rpc-get-zipcode");
        }

        @Bean
        public MessageConsumer messageConsumer() {
            return new MessageConsumer();
        }
    }
}
